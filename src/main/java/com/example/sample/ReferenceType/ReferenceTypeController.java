package com.example.sample.ReferenceType;


import com.example.sample.BaseFile.BaseFile;
import com.example.sample.BaseFile.BaseFileRepository;
import com.example.sample.BaseFile.BaseFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/reference-type")
public class ReferenceTypeController {

    private final ReferenceTypeService referenceTypeService;
    private final BaseFileService baseFileService;

    @GetMapping("/listAll")
    public Page<ReferenceType> getReferenceType(){
        return referenceTypeService.list(30);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addReferenceType(@RequestBody ReferenceType referenceType){
        referenceTypeService.create(referenceType);
        return ResponseEntity.ok("added");

    }

    @PutMapping("/{refId}/assignBaseFile/{baseId}")
    public ResponseEntity<Object> assignedBaseFile(@PathVariable("refId") Long refId,
                                                   @PathVariable("baseId") Long baseId){
        referenceTypeService.assignBaseFile(refId, baseId);
        baseFileService.assign(refId, baseId);
        return ResponseEntity.ok("Successful");
    }
}
