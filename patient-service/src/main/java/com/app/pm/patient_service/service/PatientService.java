package com.app.pm.patient_service.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.app.pm.patient_service.DTO.PatientRequestDTO;
import com.app.pm.patient_service.DTO.PatientResponseDTO;
import com.app.pm.patient_service.mapper.PatientMapper;
import com.app.pm.patient_service.model.Patient;
import com.app.pm.patient_service.repository.PatientRepository;

@Service
public class PatientService {
  private PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public List<PatientResponseDTO> getPatients() {
    List<Patient> patients = patientRepository.findAll();
    List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList(); 
    // Method Reference
    // List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toDTO).toList(); 

    return patientResponseDTOs;
    // or return directly no need of variable
    // return patients.stream().map(PatientMapper::toDTO).toList();
  }

  public PatientResponseDTO createPatient(@NonNull PatientRequestDTO patientRequestDTO) {
    Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
    return PatientMapper.toDTO(newPatient);
  }

}
