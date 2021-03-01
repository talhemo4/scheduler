package com.example.scheduler.ui.controller;

import com.example.scheduler.UserRiskRepository;
import com.example.scheduler.io.entity.UserRiskEntity;
import com.example.scheduler.service.InvoiceScheduleService;
import com.example.scheduler.service.InvoiceService;
import com.example.scheduler.service.UserService;
import com.example.scheduler.shared.InvoiceDto;
import com.example.scheduler.shared.InvoiceScheduleDto;
import com.example.scheduler.shared.UserDto;
import com.example.scheduler.ui.model.request.InvoiceScheduleDetailsRequestModel;
import com.example.scheduler.ui.model.response.ErrorMessages;
import com.example.scheduler.ui.model.response.InvoiceScheduleRest;
import com.example.scheduler.ui.model.response.OperationSystemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("schedule")

public class InvoiceScheduleController {

  private static final double RISK_TRESHOLD = 90.0;
  private static final double AMOUNT_TRESHOLD = 20000.0;

  @Autowired
  InvoiceScheduleService invoiceScheduleService;

  @Autowired
  InvoiceService invoiceService;

  @Autowired
  UserService userService;

  @Autowired
  private UserRiskRepository userRiskRepository;

  @GetMapping(path = "/{invoiceScheduleId}")
  public InvoiceScheduleRest getInvoiceSchedule(@PathVariable String invoiceScheduleId) {
    InvoiceScheduleRest returnValue = new InvoiceScheduleRest();
    InvoiceScheduleDto invoiceScheduleDto = invoiceScheduleService
        .getInvoiceScheduleByInvoiceScheduleId(Long.valueOf(invoiceScheduleId));
    BeanUtils.copyProperties(invoiceScheduleDto, returnValue);
    return returnValue;
  }

  @DeleteMapping(path = "/{invoiceId}")
  public OperationSystemModel cancelInvoiceSchedule(@PathVariable String invoiceId) {
    OperationSystemModel returnValue = new OperationSystemModel();
    returnValue.setOperationName("DELETE");
    returnValue.setOperationResult("SUCCESS");
    invoiceScheduleService.deleteInvoiceSchedule(Long.valueOf(invoiceId));
    return returnValue;
  }

  @PostMapping
  public InvoiceScheduleRest createInvoiceSchedule(
      @RequestBody InvoiceScheduleDetailsRequestModel invoiceScheduleDetails) {
    validateInvoiceSchedule(invoiceScheduleDetails);
    InvoiceScheduleRest returnValue = new InvoiceScheduleRest();
    InvoiceScheduleDto invoiceScheduleDto = new InvoiceScheduleDto();
    BeanUtils.copyProperties(invoiceScheduleDetails, invoiceScheduleDto);
    InvoiceScheduleDto createdInvoice = invoiceScheduleService
        .createInvoiceSchedule(invoiceScheduleDto);
    BeanUtils.copyProperties(createdInvoice, returnValue);
    return returnValue;
  }

  private void validateInvoiceSchedule(InvoiceScheduleDetailsRequestModel invoiceScheduleDetails) {
    if (checkForInvalidInput(invoiceScheduleDetails)) {
      throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }
    if (checkForInvalidUserRisk(invoiceScheduleDetails.getInvoiceId())) {
      throw new RuntimeException(ErrorMessages.LOW_RISK_SCORE.getErrorMessage());
    }
    if (checkForInvalidPayDate(invoiceScheduleDetails.getInvoiceId(),
        invoiceScheduleDetails.getPayDate())) {
      throw new RuntimeException(ErrorMessages.INVALID_PAY_DATE.getErrorMessage());
    }
    if (invoiceScheduleService.getInvoiceScheduleByInvoiceId(invoiceScheduleDetails.getInvoiceId())
        != null) {
      throw new RuntimeException(ErrorMessages.INVOICE_ALREADY_SCHEDULED.getErrorMessage());
    }
  }

  private boolean checkForInvalidInput(InvoiceScheduleDetailsRequestModel invoiceScheduleDetails) {
    try {
      return invoiceScheduleDetails.getPayDate() == null
          || invoiceScheduleDetails.getInvoiceId() == null;
    } catch (Exception e) {
      throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }
  }

  private boolean checkForInvalidPayDate(Long invoiceId, Long payDate) {
    InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
    Long dueDate = invoiceDto.getDueDate();
    return (payDate < System.currentTimeMillis() || payDate > dueDate);
  }

  private boolean checkForInvalidUserRisk(Long invoiceId) {
    InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
    double risk = getRisk(invoiceDto);
    double amount = invoiceDto.getAmount();
    return riskAmountLogic(risk, amount);
  }

  private boolean riskAmountLogic(double risk, double amount) {
    return risk <= RISK_TRESHOLD && amount > AMOUNT_TRESHOLD;
  }

  private double getRisk(InvoiceDto invoiceDto) {
    String customerEmail = invoiceDto.getCustomerEmail();
    UserDto userDto = userService.getUserByEmail(customerEmail);
    UserRiskEntity userRiskEntity = userRiskRepository.findByUserId(userDto.getUserId());
    return userRiskEntity.getRisk();
  }

}
