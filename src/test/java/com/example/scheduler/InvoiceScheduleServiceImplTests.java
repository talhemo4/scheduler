package com.example.scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.scheduler.io.entity.InvoiceScheduleEntity;
import com.example.scheduler.service.InvoiceScheduleServiceImpl;
import com.example.scheduler.shared.InvoiceScheduleDto;
import com.example.scheduler.shared.InvoiceScheduleStatus;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InvoiceScheduleServiceImplTests {

  final String INVOICE_ID = "1122";
  final String INVOICE_SCHEDULED_ID = "54321";
  @InjectMocks
  InvoiceScheduleServiceImpl invoiceScheduleService;
  @Mock
  InvoiceScheduleRepository invoiceScheduleRepository;
  Optional<InvoiceScheduleEntity> invoiceScheduleEntity;
  InvoiceScheduleDto invoiceScheduleDto;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    createInvoiceScheduleDto();
    createInvoiceEntity();
  }

  private void createInvoiceScheduleDto() {
    invoiceScheduleDto = new InvoiceScheduleDto();
    invoiceScheduleDto.setInvoiceId(new Long(INVOICE_ID));
    invoiceScheduleDto.setInvoiceScheduleId(new Long(INVOICE_SCHEDULED_ID));
    invoiceScheduleDto.setPayDate(new Long(2345678));
    invoiceScheduleDto.setStatus(InvoiceScheduleStatus.SCHEDULED.getName());
  }

  private void createInvoiceEntity() {
    invoiceScheduleEntity = Optional.of(new InvoiceScheduleEntity());
    invoiceScheduleEntity.get().setInvoiceId(new Long(INVOICE_ID));
    invoiceScheduleEntity.get().setInvoiceScheduleId(new Long(INVOICE_SCHEDULED_ID));
    invoiceScheduleEntity.get().setPayDate(new Long(2345678));
    invoiceScheduleEntity.get().setStatus(InvoiceScheduleStatus.SCHEDULED.getName());
  }

  @Test
  final void testGetInvoiceScheduleByInvoiceScheduleId() {
    when(invoiceScheduleRepository.findById(invoiceScheduleDto.getInvoiceScheduleId()))
        .thenReturn(invoiceScheduleEntity);
    InvoiceScheduleDto returnedValue = invoiceScheduleService
        .getInvoiceScheduleByInvoiceScheduleId(invoiceScheduleDto.getInvoiceScheduleId());
    assertNotNull(returnedValue);
  }

  @Test
  final void testGetInvoiceScheduleByInvoiceId() {
    when(invoiceScheduleRepository.findByInvoiceId(invoiceScheduleDto.getInvoiceId()))
        .thenReturn(invoiceScheduleEntity.get());
    doNothing().when(invoiceScheduleRepository).delete(invoiceScheduleEntity.get());
    InvoiceScheduleDto returnedValue = invoiceScheduleService
        .getInvoiceScheduleByInvoiceId(invoiceScheduleDto.getInvoiceId());
    assertNotNull(returnedValue);
  }

  @Test
  final void testDeleteInvoiceSchedule() {
    when(invoiceScheduleRepository.findByInvoiceId(invoiceScheduleDto.getInvoiceId()))
        .thenReturn(invoiceScheduleEntity.get());
    invoiceScheduleService.deleteInvoiceSchedule(invoiceScheduleDto.getInvoiceId());
  }

  @Test
  final void testUpdateInvoiceScheduleStatusToPay() {
    when(invoiceScheduleRepository.findByInvoiceId(invoiceScheduleDto.getInvoiceId()))
        .thenReturn(invoiceScheduleEntity.get());
    when(invoiceScheduleRepository.save(invoiceScheduleEntity.get()))
        .thenReturn(invoiceScheduleEntity.get());
    InvoiceScheduleDto returnedValue = invoiceScheduleService
        .updateInvoiceScheduleStatusToPay(invoiceScheduleDto.getInvoiceId());
    assertNotNull(returnedValue);
    assertEquals(returnedValue.getStatus(), InvoiceScheduleStatus.PAID.getName());
  }

}
