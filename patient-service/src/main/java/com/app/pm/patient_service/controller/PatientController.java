package com.app.pm.patient_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pm.patient_service.DTO.PatientResponseDTO;
import com.app.pm.patient_service.service.PatientService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


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
  
}
