package com.nilesh.PatientManager.repository;

import com.nilesh.PatientManager.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
// in the parameters pass the entity which the repository controls
// and the type of id that it controlls
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);//unique email check
}
