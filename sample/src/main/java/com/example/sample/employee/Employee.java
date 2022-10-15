package com.example.sample.employee;

import com.example.sample.constants.Status;
import com.example.sample.job.Job;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table (name = "Employee")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    @Transient
    private Integer age;
    private LocalDate dateOfBirth;
    private Status status;
    @OneToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    public Employee(Long id, String lastName, String firstName, String email, LocalDate dateOfBirth, Status status, Job job) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", status=" + status +
                ", job=" + job +
                '}';
    }
}
