package com.example.sample.dto;

import com.example.sample.auditableModel.AuditableModel;
import lombok.Data;

@Data
public class ReferenceTypeDTO extends AuditableModel {
    private Long id;
    private String name;
}
