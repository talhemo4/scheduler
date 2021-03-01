package com.example.scheduler.shared;

public enum InvoiceScheduleStatus {

  SCHEDULED("scheduled"),
  PAID("paid");

  private String status;

  InvoiceScheduleStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return status;
  }

}
