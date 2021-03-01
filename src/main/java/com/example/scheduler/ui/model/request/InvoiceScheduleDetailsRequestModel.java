package com.example.scheduler.ui.model.request;

public class InvoiceScheduleDetailsRequestModel {


  private Long invoiceId;
  private Long payDate;


  public Long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Long getPayDate() {
    return payDate;
  }

  public void setPayDate(Long payDate) {
    this.payDate = payDate;
  }
}
