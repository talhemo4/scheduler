package com.example.scheduler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.scheduler.service.InvoiceScheduleServiceImpl;
import com.example.scheduler.service.InvoiceServiceImpl;
import com.example.scheduler.shared.InvoiceDto;
import com.example.scheduler.shared.InvoiceScheduleDto;
import com.example.scheduler.shared.InvoiceScheduleStatus;
import com.example.scheduler.ui.controller.InvoiceController;
import com.example.scheduler.ui.model.response.InvoiceRest;
import com.example.scheduler.ui.model.response.InvoiceStatusRest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InvoiceControllerTests {

  final String INVOICE_ID = "1122";
  final String INVOICE_SCHEDULED_ID = "54321";
  final int PAGE = 0;
  final int LIMIT = 25;
  @InjectMocks
  InvoiceController invoiceController;
  @Mock
  InvoiceServiceImpl invoiceService;
  @Mock
  InvoiceScheduleServiceImpl invoiceScheduleService;
  InvoiceDto invoiceDto;
  InvoiceScheduleDto invoiceScheduleDto;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    createInvoiceDto();
    createInvoiceScheduleDto();
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
  final void testGetInvoice() {
    when(invoiceService.getInvoiceByInvoiceId(invoiceDto.getInvoiceId())).thenReturn(invoiceDto);
    InvoiceRest invoiceRest = invoiceController.getInvoice(INVOICE_ID);
    assertNotNull(invoiceRest);
  }

  @Test
  final void testGetInvoices() {
    List<InvoiceDto> invoiceDtos = new ArrayList<>();
    invoiceDtos.add(invoiceDto);
    when(invoiceService.getInvoices(PAGE, LIMIT)).thenReturn(invoiceDtos);
    List<InvoiceRest> invoicesRest = invoiceController.getInvoices(PAGE, LIMIT);
    assertNotNull(invoicesRest);
  }


  @Test
  final void testGetInvoiceStatus() {
    when(invoiceService.getInvoiceByInvoiceId(invoiceDto.getInvoiceId())).thenReturn(invoiceDto);
    when(invoiceScheduleService.getInvoiceScheduleByInvoiceId(new Long(INVOICE_ID)))
        .thenReturn(invoiceScheduleDto);
    InvoiceStatusRest invoiceStatusRest = invoiceController.getInvoiceStatus(INVOICE_ID);
    assertNotNull(invoiceStatusRest);
  }
}
