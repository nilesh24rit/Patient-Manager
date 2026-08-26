package com.nilesh.PatientManager.service;

import com.nilesh.PatientManager.dto.PatientRequestDTO;
import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.exceptions.EmailAlreadyExistsException;
import com.nilesh.PatientManager.exceptions.IdNotFoundException;
import com.nilesh.PatientManager.mapper.PatientMapper;
import com.nilesh.PatientManager.model.Patient;
import com.nilesh.PatientManager.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
            throw new EmailAlreadyExistsException("A patient with this email already exists: "
                    + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id,PatientRequestDTO patientRequestDTO){

        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new IdNotFoundException("Patient with id " + id + " not found"));
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: "
                    + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDob(LocalDate.parse(patientRequestDTO.getDob()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
}
