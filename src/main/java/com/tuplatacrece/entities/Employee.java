package com.tuplatacrece.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  private String dni;

  private Boolean hasBenefit;

  public Employee(){}

  public Employee(String dni, Boolean hasBenefit) {
    this.dni = dni;
    this.hasBenefit = hasBenefit;

  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public Boolean getHasBenefit() {
    return hasBenefit;
  }

  public void setHasBenefit(Boolean hasBenefit) {
    this.hasBenefit = hasBenefit;
  }
}
