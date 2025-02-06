package com.tuplatacrece.config;

import com.tuplatacrece.entities.Employee;
import com.tuplatacrece.entities.Loan;
import com.tuplatacrece.repository.EmployeeRepository;
import com.tuplatacrece.repository.LoanRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  Logger logger = Logger.getLogger(DataLoader.class.getName());

  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private LoanRepository loanRepository;


  @Override
  public void run(String... args) throws Exception {
    // Crea los empleados y guarda en la base de datos
    Employee employee = new Employee("31000000", true);
    employeeRepository.save(employee);

    Employee employee2 = new Employee("32000000", true);
    employeeRepository.save(employee2);

    Employee employee3 = new Employee("33000000", false);
    employeeRepository.save(employee3);

    // Crea los préstamos y guarda en la base de datos
    loanRepository.save(new Loan(employee, 1000000.0));

    logger.info("Datos insertados correctamente!");
  }
}

