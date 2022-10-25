package com.example.sample.employee;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public interface EmployeeService {
    Employee create(Employee employee, Long deptId);
    Collection <Employee> list(int limit);
    Employee get(Long id);
    Employee archive(Long id);

    Employee update(Long id,
                    String lastName,
                    String firstName);

}
