package com.example.scheduler.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "invoiceSchedules")
public class InvoiceScheduleEntity implements Serializable {

  private static final long serialVersionUID = 3047832527904288098L;

  @Id
  @GeneratedValue
  private long invoiceScheduleId;

  @Column(nullable = false, unique = true)
  private Long invoiceId;

  @Column(nullable = false, length = 10)
  private Long payDate;

  @Column(nullable = false, length = 50)
  private String status;

  public long getInvoiceScheduleId() {
    return invoiceScheduleId;
  }

  public void setInvoiceScheduleId(long invoiceScheduleId) {
    this.invoiceScheduleId = invoiceScheduleId;
  }

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
