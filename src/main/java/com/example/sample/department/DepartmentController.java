package com.example.sample.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/department")
public class DepartmentController {

    private final DepartmentService departmentServicee;
    private final DepartmentRepository departmentRepository;

    @GetMapping("/listAll")
    public List<Department> getJob(){
        departmentServicee.list(30);
        return departmentRepository.findAll();

    }

    @PostMapping("/register")
    public ResponseEntity<Object> addJob(@RequestBody Department department){
        departmentServicee.create(department);
        return ResponseEntity.ok("Successfully Added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable("id") Long id,
                                            @RequestParam (required = false ) String position,
                                            @RequestParam (required = false ) String department
    ){
        departmentServicee.update(id,position, department);
        return ResponseEntity.ok("Successfully Updated");

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Object> archiveJob(@PathVariable("id") Long id){
        departmentServicee.archive(id);
        return ResponseEntity.ok("");

    }

}
