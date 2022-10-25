package com.example.sample.ReferenceType;

import com.example.sample.BaseFile.BaseFile;
import com.example.sample.BaseFile.BaseFileRepository;
import com.example.sample.dto.BaseFileDTO;
import com.example.sample.dto.ReferenceTypeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ReferenceTypeImpl implements ReferenceTypeService {

    private final ReferenceTyperRepository referenceTyperRepository;
    private final BaseFileRepository baseFileRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReferenceTypeDTO create(ReferenceType referenceType) {
        referenceType.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        referenceType.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        ReferenceTypeDTO referenceTypeDTO = new ReferenceTypeDTO();
        BeanUtils.copyProperties(referenceType, referenceTypeDTO);
        return modelMapper.map(referenceTyperRepository.saveAndFlush(referenceType), ReferenceTypeDTO.class);
    }

    @Override
    public Page<ReferenceType> list(int limit) {
        return referenceTyperRepository.findAll(PageRequest.of(0, limit));
    }

    @Override
    public ReferenceType assignBaseFile(Long refId, Long baseId) {
        List<BaseFile> baseFiles = null;
        ReferenceType referenceType = referenceTyperRepository.findById(refId).get();
        BaseFile baseFile = baseFileRepository.findById(baseId).get();
        baseFiles = referenceType.getBaseFile();
        baseFiles.add(baseFile);
        referenceType.setBaseFile(baseFiles);
        return referenceTyperRepository.save(referenceType);
    }
}
