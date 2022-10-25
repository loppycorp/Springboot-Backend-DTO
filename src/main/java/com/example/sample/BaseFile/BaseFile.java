package com.example.sample.BaseFile;

import com.example.sample.ReferenceType.ReferenceType;
import com.example.sample.auditableModel.AuditableModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "base_file")
public class BaseFile extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinColumn(name = "reference_type_id")
    private ReferenceType referenceType;
}

