package com.nilesh.PatientManager.controller;

import com.nilesh.PatientManager.dto.PatientRequestDTO;
import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/AllPatients")
    public ResponseEntity<List<PatientResponseDto>> getall(){
        List<PatientResponseDto> allPatient = patientService.getAllPatient();
        if(allPatient!=null) return ResponseEntity.ok().body(allPatient);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDto);
    }
}
