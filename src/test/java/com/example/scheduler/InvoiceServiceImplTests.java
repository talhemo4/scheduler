package com.example.scheduler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.scheduler.io.entity.InvoiceEntity;
import com.example.scheduler.service.InvoiceServiceImpl;
import com.example.scheduler.shared.InvoiceDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class InvoiceServiceImplTests {

  final String INVOICE_ID = "1122";
  final int PAGE = 0;
  final int LIMIT = 25;
  @InjectMocks
  InvoiceServiceImpl invoiceService;
  @Mock
  InvoiceRepository invoiceRepository;
  @Mock
  InvoiceDto invoiceDto;
  Optional<InvoiceEntity> invoiceEntity;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    createInvoiceDto();
    createInvoiceEntity();
  }

  private void createInvoiceEntity() {
    invoiceEntity = Optional.of(new InvoiceEntity());
    invoiceEntity.get().setInvoiceId(new Long(INVOICE_ID));
    invoiceEntity.get().setAmount(51413.33);
    invoiceEntity.get().setCompanyName("Test Company");
    invoiceEntity.get().setCreationDate(new Long(12132323));
    invoiceEntity.get().setCustomerEmail("test_user@intuit.com");
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
  final void testGetInvoiceByInvoiceId() {
    when(invoiceRepository.findById(invoiceDto.getInvoiceId())).thenReturn(invoiceEntity);
    InvoiceDto returnedValue = invoiceService.getInvoiceByInvoiceId(invoiceDto.getInvoiceId());
    assertNotNull(returnedValue);
  }

  @Test
  final void testGetInvoices() {
    List<InvoiceEntity> invoiceEntities = new ArrayList<>();
    invoiceEntities.add(invoiceEntity.get());
    Page<InvoiceEntity> invoiceEntityPage = new PageImpl<>(invoiceEntities);
    Pageable pageableRequest = PageRequest.of(PAGE, LIMIT);
    when(invoiceRepository.findAll(pageableRequest)).thenReturn(invoiceEntityPage);
    List<InvoiceDto> returnedValue = invoiceService.getInvoices(PAGE, LIMIT);
    assertNotNull(returnedValue);
  }

}
