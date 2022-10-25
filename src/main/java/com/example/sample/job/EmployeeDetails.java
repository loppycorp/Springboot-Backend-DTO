package com.example.sample.job;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "employee_details")
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_details_id")
    private Long id;
    private String gender;
    @Transient
    private Integer age;
    private String email;
    private String address;
    private LocalDate dateOfBirth;

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
