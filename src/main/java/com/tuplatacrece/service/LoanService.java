package com.tuplatacrece.service;

import com.tuplatacrece.config.JwtUtil;
import com.tuplatacrece.dto.LoanDto;
import com.tuplatacrece.entities.Employee;
import com.tuplatacrece.entities.Loan;
import com.tuplatacrece.repository.EmployeeRepository;
import com.tuplatacrece.repository.LoanRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

  private final LoanRepository loanRepository;
  private final EmployeeRepository employeeRepository;
  private final JwtUtil jwtUtil;

  @Autowired
  public LoanService(LoanRepository loanRepository, EmployeeRepository employeeRepository,
      JwtUtil jwtUtil) {
    this.loanRepository = loanRepository;
    this.employeeRepository = employeeRepository;
    this.jwtUtil = jwtUtil;
  }

  public LoanDto getLoan(String authHeader) {
    String token = authHeader.substring(7);
    String dni = jwtUtil.extractDni(token);
    Employee employee = employeeRepository.findByDni(dni)
        .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    boolean hasLoanBenefit = employee.getHasBenefit();

    if (hasLoanBenefit) {
      Loan loan = loanRepository.findByEmployeeDni(dni)
          .orElseThrow(() -> new NoSuchElementException("Employee does not have loans"));
      return new LoanDto(loan.getEmployee().getDni(), loan.getLoan());
    } else {
      throw new NoSuchElementException("Employee does not have loan benefit");
    }
  }
}
