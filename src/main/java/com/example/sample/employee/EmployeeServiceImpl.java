package com.example.sample.employee;

import com.example.sample.constants.Status;
import com.example.sample.department.Department;
import com.example.sample.department.DepartmentRepository;
import com.example.sample.project.Projects;
import com.example.sample.project.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectsRepository projectsRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Employee create(Employee employee, Long deptId) {
        log.info("Register new employee: " + employee.getLastName() + "," + employee.getFirstName());
        Department department = departmentRepository.findById(deptId).get();
        employee.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        employee.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        employee.setStatus(Status.ACTIVE);
        employee.setDepartment(department);
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
                           String firstName) {
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
