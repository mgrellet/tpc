package com.tuplatacrece.controller;

import com.tuplatacrece.dto.DniDto;
import com.tuplatacrece.service.EmployeeService;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final EmployeeService employeeService;

  public AuthController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody DniDto request) {
    Optional<String> token = employeeService.authenticate(request.getDni());
    if (token.isPresent()) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Bearer " + token.get());
      return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
    }

  }
}

