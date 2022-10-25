package com.example.sample.department;

import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public interface DepartmentService {
    Department create(Department department);
    Collection<Department> list(int limit);
    Department get(Long id);
    Department archive(Long id);
    Department update(Long id,
                    String position,
                    String department);

}
