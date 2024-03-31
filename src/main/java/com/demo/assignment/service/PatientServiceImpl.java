package com.demo.assignment.service;

import com.demo.assignment.dto.response.InvoiceDTO;
import com.demo.assignment.dto.response.PatientDTO;
import com.demo.assignment.entity.Invoice;
import com.demo.assignment.entity.Patient;
import com.demo.assignment.exception.InvoiceException;
import com.demo.assignment.repository.InvoiceRepository;
import com.demo.assignment.repository.PatientRepository;
import com.demo.assignment.util.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final InvoiceRepository invoiceRepository;
    private final Mapper mapper;

    public PatientServiceImpl(PatientRepository patientRepository, InvoiceRepository invoiceRepository, Mapper mapper) {
        this.patientRepository = patientRepository;
        this.invoiceRepository = invoiceRepository;
        this.mapper = mapper;
    }

    public Patient createPatient(Patient patient) {
        patient.setAdmissionDate(LocalDateTime.now());
        return patientRepository.save(patient);
    }

    public PatientDTO getPatient(String ipNumber) throws InvoiceException {
        Optional<Patient> byIpNumber = patientRepository.findByIpNumber(ipNumber);
        List<Invoice> list = invoiceRepository.findByIpNumberAndIsDeleted(ipNumber, false);
        List<InvoiceDTO> invoiceDTOS = mapper.convertEntityToDTOList(list);
        if (byIpNumber.isPresent()) {
            PatientDTO patientDTO = mapper.getPatientDTO(byIpNumber.get());
            patientDTO.setInvoiceDTO(invoiceDTOS);
            return patientDTO;
        } else {
            throw new InvoiceException("No patient exists with the given IP number");
        }
    }
}
