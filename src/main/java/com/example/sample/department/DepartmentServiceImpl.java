package com.example.sample.department;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department create(Department department) {
        log.info("added");
        return departmentRepository.save(department);

    }

    @Override
    public Collection<Department> list(int limit) {
        log.info("List of all employees");
        return departmentRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Department get(Long id) {
        log.info("EmployeeId: " + id);
        return departmentRepository.findById(id).get();
    }

    @Override
    public Department update(Long id,
                           String position,
                           String department) {
        log.info("EmployeeId: " + id + "updated");
        Department getId = departmentRepository.findById(id)
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
    public Department archive(Long id) {
        log.info("Employee Archived");
        departmentRepository.deleteById(id);
        return null;
    }
}
