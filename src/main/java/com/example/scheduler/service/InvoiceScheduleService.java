package com.example.scheduler.service;

import com.example.scheduler.shared.InvoiceScheduleDto;

public interface InvoiceScheduleService {

  InvoiceScheduleDto createInvoiceSchedule(InvoiceScheduleDto invoiceSchedule);

  InvoiceScheduleDto updateInvoiceScheduleStatusToPay(Long invoiceId);

  void deleteInvoiceSchedule(Long invoiceId);

  InvoiceScheduleDto getInvoiceScheduleByInvoiceId(Long invoiceId);

  InvoiceScheduleDto getInvoiceScheduleByInvoiceScheduleId(Long invoiceId);

}
