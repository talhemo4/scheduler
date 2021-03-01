package com.example.scheduler;

import com.example.scheduler.io.entity.InvoiceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<InvoiceEntity, Long> {

}
