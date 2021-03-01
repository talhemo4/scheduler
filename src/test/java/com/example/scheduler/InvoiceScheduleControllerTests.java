package com.example.scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.scheduler.service.InvoiceScheduleServiceImpl;
import com.example.scheduler.shared.InvoiceDto;
import com.example.scheduler.shared.InvoiceScheduleDto;
import com.example.scheduler.shared.InvoiceScheduleStatus;
import com.example.scheduler.ui.controller.InvoiceScheduleController;
import com.example.scheduler.ui.model.response.InvoiceScheduleRest;
import com.example.scheduler.ui.model.response.OperationSystemModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InvoiceScheduleControllerTests {

  final String INVOICE_ID = "1122";
  final String INVOICE_SCHEDULED_ID = "54321";
  @InjectMocks
  InvoiceScheduleController invoiceScheduleController;
  @Mock
  InvoiceScheduleServiceImpl invoiceScheduleService;
  InvoiceDto invoiceDto;
  InvoiceScheduleDto invoiceScheduleDto;
  OperationSystemModel operationSystemModel;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    createInvoiceDto();
    createInvoiceScheduleDto();
    createOperationSystemModel();
  }

  private void createOperationSystemModel() {
    operationSystemModel = new OperationSystemModel();
    operationSystemModel.setOperationName("DELETE");
    operationSystemModel.setOperationResult("SUCCESS");
  }

  private void createInvoiceScheduleDto() {
    invoiceScheduleDto = new InvoiceScheduleDto();
    invoiceScheduleDto.setInvoiceId(new Long(INVOICE_ID));
    invoiceScheduleDto.setInvoiceScheduleId(new Long(INVOICE_SCHEDULED_ID));
    invoiceScheduleDto.setPayDate(new Long(2345678));
    invoiceScheduleDto.setStatus(InvoiceScheduleStatus.SCHEDULED.getName());
  }

  private void createInvoiceDto() {
    invoiceDto = new InvoiceDto();
    invoiceDto.setInvoiceId(new Long(INVOICE_ID));
    invoiceDto.setAmount(51413.33);
    invoiceDto.setCompanyName("Test Company");
    invoiceDto.setCreationDate(new Long(12132323));
    invoiceDto.setCustomerEmail("test_user@intuit.com");
  }

  @Test
  final void testGetInvoiceSchedule() {
    when(invoiceScheduleService
        .getInvoiceScheduleByInvoiceScheduleId(invoiceScheduleDto.getInvoiceScheduleId()))
        .thenReturn(invoiceScheduleDto);
    InvoiceScheduleRest invoiceScheduleRest = invoiceScheduleController
        .getInvoiceSchedule(INVOICE_SCHEDULED_ID);
    assertNotNull(invoiceScheduleRest);
  }

  @Test
  final void testCancelInvoiceSchedule() {
    doNothing().when(invoiceScheduleService)
        .deleteInvoiceSchedule(invoiceScheduleDto.getInvoiceId());
    OperationSystemModel returnedValue = invoiceScheduleController
        .cancelInvoiceSchedule(INVOICE_SCHEDULED_ID);
    assertNotNull(returnedValue);
    assertEquals(operationSystemModel.getOperationName(), returnedValue.getOperationName());
    assertEquals(operationSystemModel.getOperationResult(), returnedValue.getOperationResult());
  }

}
