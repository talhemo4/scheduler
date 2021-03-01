package com.example.scheduler.ui.model.response;

public class InvoiceScheduleRest {

  private long invoiceScheduleId;
  private long invoiceId;
  private Long payDate;
  private String status;

  public long getInvoiceScheduleId() {
    return invoiceScheduleId;
  }

  public void setInvoiceScheduleId(long invoiceScheduleId) {
    this.invoiceScheduleId = invoiceScheduleId;
  }

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Long getPayDate() {
    return payDate;
  }

  public void setPayDate(Long payDate) {
    this.payDate = payDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
