package com.example.sample.job;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/job")
public class EmployeeDetailsController {

    private final EmployeeDetailsService employeeDetailsService;
    private final EmployeeDetailsRepository employeeDetailsRepository;

    @GetMapping("/listAll")
    public List<EmployeeDetails> getEmployeeDetails(){
        employeeDetailsService.list(30);
        return employeeDetailsRepository.findAll();

    }

    @PostMapping("/register")
    public ResponseEntity<Object> addEmployeeDetails(@RequestBody EmployeeDetails employeeDetails){
        employeeDetailsService.create(employeeDetails);
        return ResponseEntity.ok("Successfully Added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployeeDetails(@PathVariable("id") Long id,
                                                        @RequestParam (required = false ) String gender,
                                                        @RequestParam (required = false ) String email,
                                                        @RequestParam (required = false ) String address,
                                                        @RequestParam (required = false ) LocalDate dateOfBirth
                                                        ){
        employeeDetailsService.update(id, gender, email, address, dateOfBirth);
        return ResponseEntity.ok("Successfully Updated");

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Object> archiveEmployeeDetails(@PathVariable("id") Long id){
        employeeDetailsService.archive(id);
        return ResponseEntity.ok("");

    }

}
