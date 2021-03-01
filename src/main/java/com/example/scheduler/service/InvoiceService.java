package com.example.scheduler.service;

import com.example.scheduler.shared.InvoiceDto;
import java.util.List;

public interface InvoiceService {

  InvoiceDto createInvoice(InvoiceDto invoice);

  InvoiceDto getInvoiceByInvoiceId(Long invoiceId);

  List<InvoiceDto> getInvoices(int page, int limit);

}
