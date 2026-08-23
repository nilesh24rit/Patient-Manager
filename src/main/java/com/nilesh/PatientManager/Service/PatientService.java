package com.nilesh.PatientManager.Service;

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

    public List<?> getPatient(){
        List<patient> all = patientRepository.findAll();
        return all;
    }
}
