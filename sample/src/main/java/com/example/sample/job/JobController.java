package com.example.sample.job;

import com.example.sample.ResponseEntity.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/job")
public class JobController {

    private final JobService jobService;
    private final JobRepository jobRepository;

    @GetMapping("/listAll")
    public List<Job> getJob(){
        jobService.list(30);
        return jobRepository.findAll();

    }

    @PostMapping("/register")
    public ResponseEntity<Object> addJob(@RequestBody Job job){
        jobService.create(job);
        return ResponseEntity.ok("Successfully Added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable("id") Long id,
                                            @RequestParam (required = false ) String position,
                                            @RequestParam (required = false ) String department){
        jobService.update(id,position,department);
        return ResponseEntity.ok("Successfully Updated");

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Object> archiveJob(@PathVariable("id") Long id){
        jobService.archive(id);
        return ResponseEntity.ok("");

    }

}
