package com.example.scheduler.ui.controller;

import com.example.scheduler.service.InvoiceScheduleService;
import com.example.scheduler.service.InvoiceService;
import com.example.scheduler.service.UserService;
import com.example.scheduler.shared.InvoiceDto;
import com.example.scheduler.shared.InvoiceScheduleDto;
import com.example.scheduler.ui.model.request.InvoiceDetailsRequestModel;
import com.example.scheduler.ui.model.response.ErrorMessages;
import com.example.scheduler.ui.model.response.InvoiceRest;
import com.example.scheduler.ui.model.response.InvoiceStatusRest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoices")

public class InvoiceController {

  @Autowired
  InvoiceService invoiceService;

  @Autowired
  UserService userService;


  @Autowired
  InvoiceScheduleService invoiceScheduleService;

  @GetMapping(path = "/{invoiceId}")
  public InvoiceRest getInvoice(@PathVariable String invoiceId) {
    InvoiceRest returnValue = new InvoiceRest();
    InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(Long.valueOf(invoiceId));
    BeanUtils.copyProperties(invoiceDto, returnValue);
    return returnValue;
  }

  @GetMapping()
  public List<InvoiceRest> getInvoices(@RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "25") int limit) {
    List<InvoiceRest> returnValue = new ArrayList<>();
    List<InvoiceDto> invoices = invoiceService.getInvoices(page, limit);
    for (InvoiceDto invoice : invoices) {
      InvoiceRest invoiceRest = new InvoiceRest();
      BeanUtils.copyProperties(invoice, invoiceRest);
      returnValue.add(invoiceRest);
    }
    return returnValue;
  }

  @GetMapping(path = "/status/{invoiceId}")
  public InvoiceStatusRest getInvoiceStatus(@PathVariable String invoiceId) {
    InvoiceStatusRest returnValue = new InvoiceStatusRest();
    InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(Long.valueOf(invoiceId));
    returnValue.setInvoiceId(invoiceDto.getInvoiceId());
    returnValue.setAmount(invoiceDto.getAmount());
    InvoiceScheduleDto invoiceScheduleDto = invoiceScheduleService
        .getInvoiceScheduleByInvoiceId(Long.valueOf(invoiceId));
    returnValue.setScheduled(invoiceScheduleDto != null);
    return returnValue;
  }

  @PostMapping
  public InvoiceRest createInvoice(@RequestBody InvoiceDetailsRequestModel invoiceDetails) {
    validateInvoice(invoiceDetails);
    InvoiceRest returnValue = new InvoiceRest();
    InvoiceDto invoiceDto = new InvoiceDto();
    BeanUtils.copyProperties(invoiceDetails, invoiceDto);
    InvoiceDto createdInvoice = invoiceService.createInvoice(invoiceDto);
    BeanUtils.copyProperties(createdInvoice, returnValue);
    return returnValue;
  }

  private void validateInvoice(InvoiceDetailsRequestModel invoiceDetails) {
    if (checkForInvalidInput(invoiceDetails)) {
      throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }
    if (checkForInvalidDueDate(invoiceDetails.getDueDate())) {
      throw new RuntimeException(ErrorMessages.PAST_DUE_DATE.getErrorMessage());
    }
    if (checkForInvalidCustomerEmail(invoiceDetails.getCustomerEmail())) {
      throw new RuntimeException(ErrorMessages.NO_USER.getErrorMessage());
    }

  }

  private boolean checkForInvalidInput(InvoiceDetailsRequestModel invoiceDetails) {
    try {
      return invoiceDetails.getCustomerEmail().isEmpty() || invoiceDetails.getDueDate() == null
          || invoiceDetails.getAmount() == null || invoiceDetails.getCompanyName().isEmpty()
          || invoiceDetails.getDescription().isEmpty();
    } catch (Exception e) {
      throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }
  }

  private boolean checkForInvalidCustomerEmail(String customerEmail) {
    return userService.getUserByEmail(customerEmail) == null;
  }

  private boolean checkForInvalidDueDate(Long dueDate) {
    return System.currentTimeMillis() > dueDate;
  }
}
