package com.example.sample.job;

import com.example.sample.ResponseEntity.Response;
import com.example.sample.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/job")
public class JobController {

    private final JobService jopService;

    @GetMapping("/listAll")
    public ResponseEntity<Response> getJob(){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("job", jopService.list(3)))
                        .message("Job Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerJob(@RequestBody Job job){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("job", jopService.create(job)))
                        .message("Job added")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateJob(@PathVariable("id") Long id,
                                                   @RequestParam (required = false ) String position,
                                                   @RequestParam (required = false ) String department
    ){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("job", jopService.update(id,position,department)))
                        .message("Successfuly Updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Response> archiveJob(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("job", jopService.archive(id)))
                        .message("Succesfuly Deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

}
