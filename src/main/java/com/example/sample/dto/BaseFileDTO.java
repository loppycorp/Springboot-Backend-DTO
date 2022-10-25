package com.example.sample.dto;

import com.example.sample.auditableModel.AuditableModel;
import lombok.Data;
import java.io.Serializable;

@Data
public class BaseFileDTO extends AuditableModel implements Serializable {
    private Long id;
    private String description;
    private ReferenceTypeDTO referenceType;
}