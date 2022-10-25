package com.example.sample.job;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public interface EmployeeDetailsService {
    EmployeeDetails create(EmployeeDetails employeeDetails);
    Collection <EmployeeDetails> list(int limit);
    EmployeeDetails get(Long id);

    EmployeeDetails update(Long id,
                           String gender,
                           String email,
                           String address,
                           LocalDate dateOfBirth);

    EmployeeDetails archive(Long id);

}
