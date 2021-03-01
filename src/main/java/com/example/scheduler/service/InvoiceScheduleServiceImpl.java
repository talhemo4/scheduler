package com.example.scheduler.service;

import com.example.scheduler.InvoiceScheduleRepository;
import com.example.scheduler.io.entity.InvoiceScheduleEntity;
import com.example.scheduler.shared.InvoiceScheduleDto;
import com.example.scheduler.shared.InvoiceScheduleStatus;
import com.example.scheduler.ui.model.response.ErrorMessages;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceScheduleServiceImpl implements InvoiceScheduleService {

  @Autowired
  private InvoiceScheduleRepository invoiceScheduleRepository;

  @Override
  public InvoiceScheduleDto createInvoiceSchedule(InvoiceScheduleDto invoiceSchedule) {
    InvoiceScheduleEntity invoiceScheduleEntity = new InvoiceScheduleEntity();
    BeanUtils.copyProperties(invoiceSchedule, invoiceScheduleEntity);
    invoiceScheduleEntity.setStatus(InvoiceScheduleStatus.SCHEDULED.getName());
    InvoiceScheduleEntity storedInvoiceScheduleDetails = invoiceScheduleRepository
        .save(invoiceScheduleEntity);
    InvoiceScheduleDto returnedValue = new InvoiceScheduleDto();
    BeanUtils.copyProperties(storedInvoiceScheduleDetails, returnedValue);
    return returnedValue;
  }

  // to be used by scheduled payments service
  @Override
  public InvoiceScheduleDto updateInvoiceScheduleStatusToPay(Long invoiceId) {
    InvoiceScheduleEntity invoiceScheduleEntity = invoiceScheduleRepository
        .findByInvoiceId(invoiceId);
    checkForInvoiceSchedulueExist(invoiceScheduleEntity);
    invoiceScheduleEntity.setStatus(InvoiceScheduleStatus.PAID.getName());
    InvoiceScheduleEntity updatedUserDetails = invoiceScheduleRepository
        .save(invoiceScheduleEntity);
    InvoiceScheduleDto returnedValue = new InvoiceScheduleDto();
    BeanUtils.copyProperties(updatedUserDetails, returnedValue);
    return returnedValue;
  }

  @Override
  public void deleteInvoiceSchedule(Long invoiceId) {
    InvoiceScheduleEntity invoiceScheduleEntity = invoiceScheduleRepository
        .findByInvoiceId(invoiceId);
    checkForInvoiceSchedulueExist(invoiceScheduleEntity);
    invoiceScheduleRepository.delete(invoiceScheduleEntity);
  }

  @Override
  public InvoiceScheduleDto getInvoiceScheduleByInvoiceId(Long invoiceId) {
    InvoiceScheduleDto returnedValue = new InvoiceScheduleDto();
    InvoiceScheduleEntity invoiceScheduleEntity = invoiceScheduleRepository
        .findByInvoiceId(invoiceId);
    if (invoiceScheduleEntity == null) {
      return null;
    }
    BeanUtils.copyProperties(invoiceScheduleEntity, returnedValue);
    return returnedValue;
  }

  @Override
  public InvoiceScheduleDto getInvoiceScheduleByInvoiceScheduleId(Long scheduledInvoiceId) {
    InvoiceScheduleDto returnedValue = new InvoiceScheduleDto();
    Optional<InvoiceScheduleEntity> invoiceScheduleEntity = invoiceScheduleRepository
        .findById(scheduledInvoiceId);
    if (invoiceScheduleEntity == null) {
      throw new RuntimeException(ErrorMessages.INVOICE_NOT_EXISTS.getErrorMessage());
    }
    BeanUtils.copyProperties(invoiceScheduleEntity.get(), returnedValue);
    return returnedValue;
  }

  private void checkForInvoiceSchedulueExist(InvoiceScheduleEntity invoiceScheduleEntity) {
    if (invoiceScheduleEntity == null) {
      throw new RuntimeException(ErrorMessages.INVOICE_NOT_EXISTS.getErrorMessage());
    }
  }
}
