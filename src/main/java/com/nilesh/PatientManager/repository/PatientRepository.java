package com.nilesh.PatientManager.repository;

import com.nilesh.PatientManager.model.patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
// in the parameters pass the entity which the repository controls
// and the type of id that it controlls
public interface PatientRepository extends JpaRepository<patient, UUID> {
}
