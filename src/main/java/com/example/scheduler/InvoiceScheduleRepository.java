package com.example.scheduler;

import com.example.scheduler.io.entity.InvoiceScheduleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceScheduleRepository extends
    PagingAndSortingRepository<InvoiceScheduleEntity, Long> {

  InvoiceScheduleEntity findByInvoiceId(Long invoiceId);

}
