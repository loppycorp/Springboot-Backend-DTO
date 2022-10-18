package com.example.sample.employee;

import com.example.sample.constants.Status;
import com.example.sample.job.Job;
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
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Long empId;
    private String lastName;
    private String firstName;
    private String email;
    @Transient
    private Integer age;
    private LocalDate dateOfBirth;
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_job_id")
    private Job job;

    public Employee(Long empId, String lastName, String firstName, String email, LocalDate dateOfBirth, Status status, Job job) {
        this.empId = empId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.job = job;
    }

    public Employee(String lastName, String firstName, String email, LocalDate dateOfBirth, Status status, Job job) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.job = job;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long id) {
        this.empId = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }


}
