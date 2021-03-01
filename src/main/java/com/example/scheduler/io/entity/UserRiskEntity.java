package com.example.scheduler.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "userRisk")
public class UserRiskEntity implements Serializable {

  private static final long serialVersionUID = 2811078110404090580L;

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false, unique = true)
  private Long userId;

  @Column(nullable = false)
  private double risk;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public double getRisk() {
    return risk;
  }

  public void setRisk(double risk) {
    this.risk = risk;
  }
}
