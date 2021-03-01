package com.example.scheduler.ui.model.response;

public enum ErrorMessages {

  MISSING_REQUIRED_FIELD("Missing required fields"),
  PAST_DUE_DATE("Due Date can't be in the past"),
  NO_USER("There is no User with this email in the system"),
  LOW_RISK_SCORE("Your risk score is too low to schedule this amount of invoice"),
  INVALID_PAY_DATE("Pay Date can't be in the past or after invoice's Due Date"),
  INVOICE_NOT_EXISTS("Invoice not exist in the system"),
  USER_EXISTS("User already exists"),
  USER_NOT_EXISTS("User not exist in the system"),
  INVOICE_ALREADY_SCHEDULED("This invoice has been already scheduled");

  private String errorMessage;

  ErrorMessages(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}
