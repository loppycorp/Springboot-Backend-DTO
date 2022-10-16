package com.example.sample.job;

import com.example.sample.employee.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public interface JobService {
    Job create(Job job);
    Collection <Job> list(int limit);
    Job get(Long id);
    Job archive(Long id);
    Job update(Long id,
                    String position,
                    String department);

}
