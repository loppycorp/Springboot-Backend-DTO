package com.example.sample.job;

import com.example.sample.employee.Employee;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "Job")
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String position;
    private String department;
    @OneToOne(mappedBy = "job")
    private Employee employee;

    public Job(Long id, String position, String department) {
        this.id = id;
        this.position = position;
        this.department = department;
    }

    public Job(String position, String department, Employee employee) {
        this.position = position;
        this.department = department;
        this.employee = employee;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
