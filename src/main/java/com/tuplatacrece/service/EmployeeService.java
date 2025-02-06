package com.tuplatacrece.service;

import com.tuplatacrece.config.JwtUtil;
import com.tuplatacrece.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final JwtUtil jwtUtil;


  public EmployeeService(EmployeeRepository employeeRepository, JwtUtil jwtUtil) {
    this.employeeRepository = employeeRepository;
    this.jwtUtil = jwtUtil;
  }

  public Optional<String> authenticate(String dni) {
    if(employeeRepository.findByDni(dni).isPresent()) {
      return Optional.of(jwtUtil.generateToken(dni));
    }
    return Optional.empty();
  }

}
