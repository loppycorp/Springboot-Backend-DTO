package com.example.sample.employee;

import com.example.sample.constants.Status;
import com.example.sample.job.Job;
import com.example.sample.job.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    @Override
    public Employee create(Employee employee, Long jobId) {
        log.info("Register new employee: " + employee.getLastName() + "," + employee.getFirstName());
        employee.setStatus(Status.ACTIVE);
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalStateException(jobId + "doesn't exist"));
        employee.setJob(job);
        Optional<Employee> verifyEmail = employeeRepository
                .findEmployeeByEmail(employee.getEmail());
        if (verifyEmail.isPresent()) {
            throw new IllegalStateException(employee.getEmail() + "is already taken");
        }

            return employeeRepository.save(employee);

    }

    @Override
    public Collection<Employee> list(int limit) {
        log.info("List of all employees");
        return employeeRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Employee get(Long id) {
        log.info("EmployeeId: " + id);
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee update(Long id,
                           String lastName,
                           String firstName,
                           String email,
                           LocalDate dateOfBirth) {
        log.info("EmployeeId: " + id + "updated");
        Employee getId = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        id + "doesn't exist"));
        if (lastName != null && lastName.length() > 0 && !Objects.equals(getId.getLastName(), lastName)) {
            getId.setLastName(lastName);
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(getId.getFirstName(), firstName)) {
            getId.setFirstName(firstName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(getId.getEmail(), email)) {
            getId.setEmail(email);
        }
        if (dateOfBirth != null && !Objects.equals(getId.getDateOfBirth(), dateOfBirth)) {
            getId.setDateOfBirth(dateOfBirth);
        }

        return getId;
    }

    @Override
    public Employee archive(Long id) {
        log.info("Employee Archived");
        Employee getId = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        id + "doesn't exist"));
        getId.setStatus(Status.DELETED);
        return getId;
    }
}
