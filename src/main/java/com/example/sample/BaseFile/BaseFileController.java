package com.example.sample.BaseFile;


import com.example.sample.ReferenceType.ReferenceType;
import com.example.sample.ReferenceType.ReferenceTypeService;
import com.example.sample.dto.BaseFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/base-file")
public class BaseFileController {

    private final BaseFileService baseFileService;
    private final BaseFileRepository baseFileRepository;

    @GetMapping("/listAll")
    public Page<BaseFile> getReferenceType(){
        return baseFileService.list(30);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BaseFileDTO> getById(@PathVariable("id") Long baseId){
        BaseFileDTO baseFileDTO = baseFileService.get(baseId);
        return ResponseEntity.ok(baseFileDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addReferenceType(@RequestBody BaseFile baseFile){
        baseFileService.create(baseFile);
        return ResponseEntity.ok("added");

    }
}
