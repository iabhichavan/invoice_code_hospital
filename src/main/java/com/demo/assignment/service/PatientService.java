package com.demo.assignment.service;

import com.demo.assignment.dto.response.PatientDTO;
import com.demo.assignment.entity.Patient;
import com.demo.assignment.exception.InvoiceException;

public interface PatientService {
    Patient createPatient(Patient patient);

    PatientDTO getPatient(String ipNumber) throws InvoiceException;
}
