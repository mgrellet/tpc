package com.tuplatacrece.repository;

import com.tuplatacrece.entities.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

  Optional<Loan> findByEmployeeDni(String dni);
}
