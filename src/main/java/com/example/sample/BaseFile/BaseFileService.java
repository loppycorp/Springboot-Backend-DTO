package com.example.sample.BaseFile;

import com.example.sample.dto.BaseFileDTO;
import org.springframework.data.domain.Page;

public interface BaseFileService {

    BaseFileDTO create(BaseFile baseFile);
    Page<BaseFile> list(int limit);
    BaseFileDTO get(Long baseId);

    BaseFileDTO assign(Long refId, Long baseId);

}
