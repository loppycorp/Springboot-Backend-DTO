package com.example.sample.BaseFile;

import com.example.sample.ReferenceType.ReferenceType;
import com.example.sample.ReferenceType.ReferenceTyperRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class BaseFileImpl implements BaseFileService {

    private final ReferenceTyperRepository referenceTyperRepository;
    private final BaseFileRepository baseFileRepository;
    private final ModelMapper modelMapper;


    @Override
    public BaseFileDTO create(BaseFile baseFile) {
        baseFile.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        baseFile.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        BaseFileDTO baseFileDTO = new BaseFileDTO();
        BeanUtils.copyProperties(baseFile, baseFileDTO);
        return modelMapper.map(baseFileRepository.saveAndFlush(baseFile), BaseFileDTO.class);
    }

    @Override
    public Page<BaseFile> list(int limit) {
        return baseFileRepository.findAll(PageRequest.of(0, limit));
    }

    @Override
    public BaseFileDTO get(Long baseId) {
        BaseFile baseFile = baseFileRepository.findById(baseId).get();
        if (baseFile != null){
            BaseFileDTO baseFileDTO = new BaseFileDTO();
            BeanUtils.copyProperties(baseFile, baseFileDTO);
            return modelMapper.map(baseFileRepository.saveAndFlush(baseFile), BaseFileDTO.class);
        }
        throw new RuntimeException();
    }

    @Override
    public BaseFileDTO assign(Long refId, Long baseId) {
        ReferenceType referenceType = referenceTyperRepository.findById(refId).get();
        BaseFile baseFile = baseFileRepository.findById(baseId).get();
        Optional<BaseFile> optionalBaseFile = baseFileRepository.findById(baseId);
        if (optionalBaseFile.isPresent()){
            BaseFile baseFileUpdate = optionalBaseFile.get();
            if (baseFileRepository.existsById(baseId)) {
                baseFileUpdate.setCreateDate(baseFile.getCreateDate());
                baseFileUpdate.setModifiedDate(baseFile.getModifiedDate());
                baseFileUpdate.setDescription(baseFile.getDescription());
                baseFileUpdate.setReferenceType(referenceType);
                return modelMapper.map(baseFileRepository.saveAndFlush(baseFileUpdate), BaseFileDTO.class);
            }
        }
        throw new IllegalStateException("CA");
    }
}
