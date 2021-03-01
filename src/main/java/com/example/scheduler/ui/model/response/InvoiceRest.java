package com.example.scheduler.ui.model.response;

public class InvoiceRest {

  private long invoiceId;
  private Double amount;
  private Long creationDate;
  private String description;
  private Long dueDate;
  private String companyName;
  private String customerEmail;

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

  public Long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Long creationDate) {
    this.creationDate = creationDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getDueDate() {
    return dueDate;
  }

  public void setDueDate(Long dueDate) {
    this.dueDate = dueDate;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }
}
