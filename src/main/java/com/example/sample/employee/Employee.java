package com.example.sample.employee;

import com.example.sample.auditableModel.AuditableModel;
import com.example.sample.constants.Status;
import com.example.sample.department.Department;
import com.example.sample.project.Projects;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends AuditableModel implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Long id;
    private String lastName;
    private String firstName;
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @ManyToMany
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private List<Projects> projects;

}
