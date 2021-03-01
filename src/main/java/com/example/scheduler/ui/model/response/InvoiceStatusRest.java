package com.example.scheduler.ui.model.response;

public class InvoiceStatusRest {

  private long invoiceId;
  private Double amount;
  private boolean scheduled;

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public boolean isScheduled() {
    return scheduled;
  }

  public void setScheduled(boolean scheduled) {
    this.scheduled = scheduled;
  }
}
