package com.nilesh.PatientManager.controller;

import com.nilesh.PatientManager.dto.PatientRequestDTO;
import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all-patient")
    public ResponseEntity<List<PatientResponseDto>> getall(){
        List<PatientResponseDto> allPatient = patientService.getAllPatient();
        if(allPatient!=null) return ResponseEntity.ok().body(allPatient);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-patient")
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<PatientResponseDto> deletePatient(@PathVariable UUID id) {
        ResponseEntity<PatientResponseDto> response = patientService.deletePatient(id);
        return response;
    }
}
