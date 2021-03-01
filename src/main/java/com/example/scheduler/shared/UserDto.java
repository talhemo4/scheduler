package com.example.scheduler.shared;

import java.io.Serializable;

public class UserDto implements Serializable {

  private static final long serialVersionUID = 8583517997323239687L;
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
