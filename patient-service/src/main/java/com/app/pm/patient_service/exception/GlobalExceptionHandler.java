package com.app.pm.patient_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errorsMap = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(
      error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
      
    Map<String, Object> response = new HashMap<>();
    response.put("status", 400);
    response.put("success", false);
    response.put("errors", errorsMap);
    return ResponseEntity.badRequest().body(response);

  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<Map<String, Object>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
    log.warn("Email address already exists {}", ex.getMessage());

    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Email address already exists");
    Map<String, Object> response = new HashMap<>();
    response.put("status", 400);
    response.put("success", false);
    response.put("errors", errors);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handlePatientNotFoundException(PatientNotFoundException ex) {
    log.warn("Patient not found {} ", ex.getMessage());

    Map<String, String> errors = new HashMap<>();
    errors.put("message", "Patient not found");
    Map<String, Object> response = new HashMap<>();
    response.put("status", 400);
    response.put("success", false);
    response.put("errors", errors);
    return ResponseEntity.badRequest().body(response);
  }
}
