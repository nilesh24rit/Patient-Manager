package com.nilesh.PatientManager.service;

import com.nilesh.PatientManager.dto.PatientRequestDTO;
import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.mapper.PatientMapper;
import com.nilesh.PatientManager.model.Patient;
import com.nilesh.PatientManager.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService (PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }// better than @Autowired can use final and easier testing

    public List<PatientResponseDto> getAllPatient(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDTO patientRequestDTO){
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExixtsException("A patient with this email already exists: "
                    + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
