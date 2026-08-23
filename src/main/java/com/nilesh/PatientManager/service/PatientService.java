package com.nilesh.PatientManager.service;

import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.model.patient;
import com.nilesh.PatientManager.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService (PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }// better than @Autowired can use final and easier testing

    public List<PatientResponseDto> getPatient(){
        List<PatientResponseDto> all = patientRepository.findAll();
        return all;
    }
}
