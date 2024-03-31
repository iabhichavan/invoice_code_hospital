package com.demo.assignment.repository;

import com.demo.assignment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    boolean existsByIpNumber(String ipNumber);

    Optional<Patient> findByIpNumber(String ipNumber);
}

