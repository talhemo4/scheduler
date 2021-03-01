package com.example.scheduler.service;

import com.example.scheduler.InvoiceRepository;
import com.example.scheduler.io.entity.InvoiceEntity;
import com.example.scheduler.shared.InvoiceDto;
import com.example.scheduler.ui.model.response.ErrorMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;


  @Override
  public InvoiceDto createInvoice(InvoiceDto invoice) {
    InvoiceEntity invoiceEntity = new InvoiceEntity();
    BeanUtils.copyProperties(invoice, invoiceEntity);
    invoiceEntity.setCreationDate(System.currentTimeMillis());
    InvoiceEntity storedInvoiceDetails = invoiceRepository.save(invoiceEntity);
    InvoiceDto returnedValue = new InvoiceDto();
    BeanUtils.copyProperties(storedInvoiceDetails, returnedValue);
    return returnedValue;
  }

  @Override
  public InvoiceDto getInvoiceByInvoiceId(Long invoiceId) {
    InvoiceDto returnedValue = new InvoiceDto();
    Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(invoiceId);
    if (invoiceEntity == null) {
      throw new RuntimeException(ErrorMessages.INVOICE_NOT_EXISTS.getErrorMessage());
    }
    BeanUtils.copyProperties(invoiceEntity.get(), returnedValue);
    return returnedValue;
  }

  @Override
  public List<InvoiceDto> getInvoices(int page, int limit) {
    List<InvoiceDto> returnValue = new ArrayList<>();
    Pageable pageableRequest = PageRequest.of(page, limit);
    Page<InvoiceEntity> invoicesPage = invoiceRepository.findAll(pageableRequest);
    List<InvoiceEntity> invoices = invoicesPage.getContent();
    for (InvoiceEntity invoice : invoices) {
      InvoiceDto invoiceDto = new InvoiceDto();
      BeanUtils.copyProperties(invoice, invoiceDto);
      returnValue.add(invoiceDto);
    }
    return returnValue;
  }
}
