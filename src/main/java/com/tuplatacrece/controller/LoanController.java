package com.tuplatacrece.controller;

import com.tuplatacrece.dto.LoanDto;
import com.tuplatacrece.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

  private final LoanService loanService;

  public LoanController(LoanService loanService) {
    this.loanService = loanService;
  }

  @GetMapping
  public ResponseEntity<?> getLoan(@RequestHeader("Authorization") String authHeader) {
    try {
      LoanDto loan = loanService.getLoan(authHeader);
      return ResponseEntity.ok(loan);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(e.getMessage());
    }
  }

}
