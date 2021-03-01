package com.example.scheduler.ui.model.request;

public class InvoiceDetailsRequestModel {

  private Double amount;
  private String description;
  private Long dueDate;
  private String companyName;
  private String customerEmail;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
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
