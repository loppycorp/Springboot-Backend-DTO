package com.example.sample.job;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    Optional<EmployeeDetails> findJobById (long id);
}
