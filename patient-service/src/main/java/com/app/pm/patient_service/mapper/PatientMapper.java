package com.app.pm.patient_service.mapper;

// import java.time.LocalDate;

import com.app.pm.patient_service.DTO.PatientRequestDTO;
import com.app.pm.patient_service.DTO.PatientResponseDTO;
import com.app.pm.patient_service.model.Patient;

import org.springframework.lang.NonNull;



public class PatientMapper {
  public static PatientResponseDTO toDTO(Patient patient) {
    PatientResponseDTO patientDTO = new PatientResponseDTO();

    patientDTO.setId(patient.getId().toString());
    patientDTO.setName(patient.getName());
    patientDTO.setAddress(patient.getAddress());
    patientDTO.setEmail(patient.getEmail());
    patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

    return patientDTO;
  }

  @NonNull
  public static Patient toModel(PatientRequestDTO patientRequestDTO) {
    Patient patient = new Patient();
    patient.setName(patientRequestDTO.getName());
    patient.setEmail(patientRequestDTO.getEmail());
    patient.setAddress(patientRequestDTO.getAddress());
    patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());

    return patient;
  }
}
