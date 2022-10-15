package com.example.sample.job;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    Optional<Job> findJobById (long id);
}
