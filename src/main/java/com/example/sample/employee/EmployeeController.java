package com.example.sample.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/demo-project/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/listAll")
    public List<Employee> getEmployee(){
        employeeService.list(30);
        return employeeRepository.findAll();


    }

    @GetMapping("/getEmployee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") Long id){
        employeeService.get(id);
        return employeeRepository.findById(id);

    }
    @PostMapping("/register/department-id/{empId}/project-id/{prjId}")
    public ResponseEntity<Object> create(
            @RequestBody Employee employee,
            @PathVariable("empId") Long empId){
        return ResponseEntity.ok(employeeService.create(employee,empId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id,
                                                   @RequestParam (required = false ) String lastName,
                                                   @RequestParam (required = false ) String firstName
    ){
        employeeService.update(id,lastName,firstName);
        return ResponseEntity.ok("Successfully Update");

    }
    @DeleteMapping("/archive/{id}")
    public ResponseEntity<Object> archiveEmployee(@PathVariable("id") Long id){
        employeeService.archive(id);
        return ResponseEntity.ok("Employee Archived");

    }

}
