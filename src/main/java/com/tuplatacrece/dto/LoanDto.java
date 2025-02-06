package com.tuplatacrece.dto;

public class LoanDto {

  private String dni;
  private Double loan;

  public LoanDto(String dni, Double loan) {
    this.dni = dni;
    this.loan = loan;
  }

  public String getDni() {
    return dni;
  }
  public void setDni(String dni) {
    this.dni = dni;
  }
  public Double getLoan() {
    return loan;
  }
  public void setLoan(Double loan) {
    this.loan = loan;
  }
}
