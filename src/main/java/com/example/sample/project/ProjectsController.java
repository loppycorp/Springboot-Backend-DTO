package com.example.sample.project;

import com.example.sample.department.Department;
import com.example.sample.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/projects")
public class ProjectsController {

    private final ProjectsService projectsServices;
    private final ProjectsRepository projectsRepository;

    @GetMapping("/listAll")
    public List<Projects> getProjects(){
        projectsServices.list(30);
        return projectsRepository.findAll();

    }

    @PostMapping("/add")
    public ResponseEntity<Object> addJob(@RequestBody Projects projects){
        projectsServices.create(projects);
        return ResponseEntity.ok("Successfully Added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable("id") Long id,
                                            @RequestParam (required = false ) String projects
    ){
        projectsServices.update(id,projects);
        return ResponseEntity.ok("Successfully Updated");

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Object> archiveJob(@PathVariable("id") Long id){
        projectsServices.archive(id);
        return ResponseEntity.ok("");

    }

}
