package com.example.scheduler.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "invoices")
public class InvoiceEntity implements Serializable {

  private static final long serialVersionUID = 7737810885759584177L;

  @Id
  @GeneratedValue
  private long invoiceId;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false, length = 250)
  private String description;

  @Column(nullable = false)
  private Long creationDate;

  @Column(nullable = false)
  private Long dueDate;

  @Column(nullable = false, length = 50)
  private String companyName;

  @Column(nullable = false, length = 50)
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Long creationDate) {
    this.creationDate = creationDate;
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
