package com.example.sample.ReferenceType;

import com.example.sample.BaseFile.BaseFile;
import com.example.sample.auditableModel.AuditableModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "reference_type")
public class ReferenceType extends AuditableModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_file_id")
    private List<BaseFile> baseFile;
}
