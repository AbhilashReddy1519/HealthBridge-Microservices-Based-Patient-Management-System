package com.app.pm.patient_service.service;

// import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.app.pm.patient_service.DTO.PatientRequestDTO;
import com.app.pm.patient_service.DTO.PatientResponseDTO;
import com.app.pm.patient_service.exception.EmailAlreadyExistsException;
import com.app.pm.patient_service.exception.PatientNotFoundException;
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

    if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
      throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
    }

    Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
    return PatientMapper.toDTO(newPatient);
  }

  public PatientResponseDTO updatePatient(@NonNull UUID id, PatientRequestDTO patientRequestDTO) {

    Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

    if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
      throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
    }

    patient.setName(patientRequestDTO.getName());
    patient.setAddress(patientRequestDTO.getAddress());
    patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
    patient.setEmail(patientRequestDTO.getEmail());

    Patient updatedPatient = patientRepository.save(patient);
    return PatientMapper.toDTO(updatedPatient);
  }

  public void deletePatient(@NonNull UUID id) {
    patientRepository.deleteById(id);
  }

}
