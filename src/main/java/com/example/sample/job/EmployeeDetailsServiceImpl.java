package com.example.sample.job;

import com.example.sample.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDetails create(EmployeeDetails employeeDetails) {
        log.info("added");
        return employeeDetailsRepository.save(employeeDetails);

    }

    @Override
    public Collection<EmployeeDetails> list(int limit) {
        log.info("List of all employees");
        return employeeDetailsRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public EmployeeDetails get(Long id) {
        log.info("EmployeeId: " + id);
        return employeeDetailsRepository.findById(id).get();
    }

    @Override
    public EmployeeDetails update(Long id,
                                  String gender,
                                  String email,
                                  String address,
                                  LocalDate dateOfBirth) {
        log.info("EmployeeId: " + id + "updated");
        EmployeeDetails getId = employeeDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        id + "doesn't exist"));
        if (gender != null && gender.length() > 0 && !Objects.equals(getId.getGender(), gender)) {
            getId.setGender(gender);
        }

        if (email != null && email.length() > 0 && !Objects.equals(getId.getEmail(), email)) {
            getId.setEmail(email);
        }
        if (address != null && address.length() > 0 && !Objects.equals(getId.getAddress(), address)) {
            getId.setAddress(address);
        }
        if (dateOfBirth != null && !Objects.equals(getId.getDateOfBirth(), dateOfBirth)) {
            getId.setDateOfBirth(dateOfBirth);
        }


        return getId;
    }

    @Override
    public EmployeeDetails archive(Long id) {
        log.info("Employee Archived");
        employeeDetailsRepository.deleteById(id);
        return null;
    }
}
