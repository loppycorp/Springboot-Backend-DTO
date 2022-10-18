package com.example.sample.job;

import com.example.sample.employee.Employee;
import com.example.sample.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Job create(Job  job) {
        log.info("added");
        return jobRepository.save(job);

    }

    @Override
    public Collection<Job> list(int limit) {
        log.info("List of all employees");
        return jobRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Job get(Long id) {
        log.info("EmployeeId: " + id);
        return jobRepository.findById(id).get();
    }

    @Override
    public Job update(Long id,
                           String position,
                           String department) {
        log.info("EmployeeId: " + id + "updated");
        Job getId = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        id + "doesn't exist"));
        if (position != null && position.length() > 0 && !Objects.equals(getId.getPosition(), position)) {
            getId.setPosition(position);
        }

        if (department != null && department.length() > 0 && !Objects.equals(getId.getDepartment(), department)) {
            getId.setDepartment(department);
        }

        return getId;
    }

    @Override
    public Job archive(Long id) {
        log.info("Employee Archived");
        jobRepository.deleteById(id);
        return null;
    }
}
