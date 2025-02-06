package com.tuplatacrece.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan {

  @Id
  private int id;

  @OneToOne
  @JoinColumn(name = "dni", referencedColumnName = "dni")
  private Employee employee;

  private Double loan;

  public Loan(Employee employee, Double loan) {
    this.employee = employee;
    this.loan = loan;
  }

  public Loan() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Double getLoan() {
    return loan;
  }

  public void setLoan(Double loan) {
    this.loan = loan;
  }
}
