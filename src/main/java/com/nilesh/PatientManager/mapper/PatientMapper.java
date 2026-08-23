package com.nilesh.PatientManager.mapper;

import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.model.Patient;

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
}
