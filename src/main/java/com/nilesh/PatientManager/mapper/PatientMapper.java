package com.nilesh.PatientManager.mapper;

import com.nilesh.PatientManager.dto.PatientRequestDTO;
import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDto toDTO(Patient patient){

        PatientResponseDto patientDTO=new PatientResponseDto();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmial(patient.getEmail());
        patientDTO.setDob(patient.getDob().toString());
        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO){
        Patient patient=new Patient();
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDob(LocalDate.parse(patientRequestDTO.getDob()));
        patient.setRegestrationDate(LocalDate.parse(patientRequestDTO.getRegestrationDate()));
        return patient;
    }
}
