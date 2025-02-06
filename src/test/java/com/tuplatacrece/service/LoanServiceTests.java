package com.tuplatacrece.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.tuplatacrece.config.JwtUtil;
import com.tuplatacrece.dto.LoanDto;
import com.tuplatacrece.entities.Employee;
import com.tuplatacrece.entities.Loan;
import com.tuplatacrece.repository.EmployeeRepository;
import com.tuplatacrece.repository.LoanRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTests {

  @Mock
  private LoanRepository loanRepository;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private JwtUtil jwtUtil;

  @InjectMocks
  private LoanService loanService;

  private static final String AUTH_HEADER = "Bearer token123";
  private static final String DNI = "30000000";

  @Test
  void shouldReturnLoanDtoWhenEmployeeHasBenefitAndLoanExists() {
    Employee employee = new Employee();
    employee.setDni(DNI);
    employee.setHasBenefit(true);

    Loan loan = new Loan();
    loan.setEmployee(employee);
    loan.setLoan(1000.0);

    when(jwtUtil.extractDni(anyString())).thenReturn(DNI);
    when(employeeRepository.findByDni(DNI)).thenReturn(Optional.of(employee));
    when(loanRepository.findByEmployeeDni(DNI)).thenReturn(Optional.of(loan));

    LoanDto result = loanService.getLoan(AUTH_HEADER);

    assertNotNull(result);
    assertEquals(DNI, result.getDni());
    assertEquals(1000.0, result.getLoan());
  }

  @Test
  void shouldThrowExceptionWhenEmployeeHasBenefitButNoLoan() {
    Employee employee = new Employee();
    employee.setDni(DNI);
    employee.setHasBenefit(true);

    when(jwtUtil.extractDni(anyString())).thenReturn(DNI);
    when(employeeRepository.findByDni(DNI)).thenReturn(Optional.of(employee));
    when(loanRepository.findByEmployeeDni(DNI)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> loanService.getLoan(AUTH_HEADER));
  }

  @Test
  void shouldThrowExceptionWhenEmployeeHasNoBenefit() {
    Employee employee = new Employee();
    employee.setDni(DNI);
    employee.setHasBenefit(false);

    when(jwtUtil.extractDni(anyString())).thenReturn(DNI);
    when(employeeRepository.findByDni(DNI)).thenReturn(Optional.of(employee));

    assertThrows(NoSuchElementException.class, () -> loanService.getLoan(AUTH_HEADER));
  }

  @Test
  void shouldThrowExceptionWhenEmployeeNotFound() {
    when(jwtUtil.extractDni(anyString())).thenReturn(DNI);
    when(employeeRepository.findByDni(DNI)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> loanService.getLoan(AUTH_HEADER));
  }

}
