package com.app.pm.patient_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pm.patient_service.DTO.PatientRequestDTO;
import com.app.pm.patient_service.DTO.PatientResponseDTO;
import com.app.pm.patient_service.DTO.validators.CreatePatientValidationGroup;
import com.app.pm.patient_service.service.PatientService;


import jakarta.validation.groups.Default;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/patients")
public class PatientController {
  private final PatientService patientService;

  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  // or for this remove RequestMapping @GetMapping("/patients")
  @GetMapping
  public ResponseEntity<List<PatientResponseDTO>> getPatients() {
      List<PatientResponseDTO> patients = patientService.getPatients();

      return ResponseEntity.ok().body(patients);
  }

  @PostMapping
  // @Valid or @Validated
  public ResponseEntity<PatientResponseDTO> createPatient(@NonNull @Validated({Default.class, CreatePatientValidationGroup.class})  @RequestBody PatientRequestDTO patientRequestDTO) {
    PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

    return ResponseEntity.ok().body(patientResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PatientResponseDTO> updatePatient(@NonNull @PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {

    PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);

    return ResponseEntity.ok().body(patientResponseDTO);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatient(@NonNull @PathVariable UUID id) {
    patientService.deletePatient(id);
    return ResponseEntity.noContent().build();
  }
  
}
