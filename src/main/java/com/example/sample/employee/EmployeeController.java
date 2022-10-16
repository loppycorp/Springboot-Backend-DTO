package com.example.sample.employee;

import com.example.sample.ResponseEntity.Response;
import com.example.sample.job.Job;
import com.example.sample.job.JobRepository;
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
@RequestMapping(path = "api/demo-project/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JobRepository jobRepository;

    @GetMapping("/listAll")
    public ResponseEntity<Response> getEmployee(){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("employees", employeeService.list(3)))
                        .message("Employee Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

    @PostMapping("/register/{id}")
    public ResponseEntity<Response> registerEmployee(@RequestBody Employee employee,
                                                     @PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("employees", employeeService.create(employee, id)))
                        .message("New Employee Registered")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateEmployee(@PathVariable("id") Long id,
                                                   @RequestParam (required = false ) String lastName,
                                                   @RequestParam (required = false ) String firstName,
                                                   @RequestParam (required = false ) String email,
                                                   @RequestParam (required = false ) LocalDate dateOfBirth
    ){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("employees", employeeService.update(id,lastName,firstName,email,dateOfBirth)))
                        .message("Successfuly Updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Response> archiveEmployee(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("employees", employeeService.archive(id)))
                        .message("Succesfuly Deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

}
