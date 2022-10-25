package com.example.sample.ReferenceType;

import com.example.sample.dto.ReferenceTypeDTO;
import org.springframework.data.domain.Page;

public interface ReferenceTypeService {

    ReferenceTypeDTO create(ReferenceType referenceType);
    Page<ReferenceType> list(int limit);

    ReferenceType assignBaseFile(Long refId, Long baseId);
}
