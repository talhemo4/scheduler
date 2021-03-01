package com.example.scheduler.shared;

import java.io.Serializable;

public class InvoiceScheduleDto implements Serializable {

  private static final long serialVersionUID = 4610819130008258583L;

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
