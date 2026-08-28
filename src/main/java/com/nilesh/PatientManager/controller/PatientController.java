package com.nilesh.PatientManager.controller;

import com.nilesh.PatientManager.dto.PatientRequestDTO;
import com.nilesh.PatientManager.dto.PatientResponseDto;
import com.nilesh.PatientManager.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Patient Controller", description = "Endpoints for managing patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all-patient")
    @Operation(
            summary = "Get All Patients",
            responses = {@ApiResponse(responseCode = "200", description = "Patients retrieved"),
                    @ApiResponse(responseCode = "404", description = "No patients found")})
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> allPatient = patientService.getAllPatient();
        if (allPatient != null) {
            return ResponseEntity.ok().body(allPatient);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-patient")
    @Operation(summary = "Create Patient", description = "Create a new patient", requestBody =
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Patient details to create", required = true, content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = PatientRequestDTO.class))),
            responses = {@ApiResponse(responseCode = "200", description = "Patient created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")})
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @PutMapping("/update-patient/{id}")
    @Operation(summary = "Update Patient", description = "Update an existing patient",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated patient details", required = true, content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = PatientRequestDTO.class))),
            responses = {@ApiResponse(responseCode = "200", description = "Patient updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "404", description = "Patient not found")})
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @DeleteMapping("/delete-patient/{id}")
    @Operation(summary = "Delete Patient", description = "Delete an existing patient",
            responses = {@ApiResponse(responseCode = "200", description = "Patient deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Patient not found")})
    public ResponseEntity<PatientResponseDto> deletePatient(@PathVariable UUID id) {
        return patientService.deletePatient(id);
    }
}