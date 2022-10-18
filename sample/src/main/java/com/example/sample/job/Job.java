package com.example.sample.job;

import com.example.sample.employee.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;
    private String position;
    private String department;


    public Job(Long jobId, String position, String department) {
        this.jobId = jobId;
        this.position = position;
        this.department = department;
    }

    public Job(String position, String department) {
        this.position = position;
        this.department = department;
    }

    public Long Jobid() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

}
