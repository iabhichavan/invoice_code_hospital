package com.demo.assignment.controller;


import com.demo.assignment.dto.response.PatientDTO;
import com.demo.assignment.entity.Patient;
import com.demo.assignment.exception.InvoiceException;
import com.demo.assignment.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/patient/v1")
@Tag(name = "Patient controller", description = "All the operation related to patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @Operation(summary = "Creation of Patient")
    @ApiResponse(responseCode = "200", description = "Successfully invoice created", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> createPatient(@RequestBody Patient request) {
        Patient patient = patientService.createPatient(request);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/{ipNumber}")
    @Operation(summary = "Retrieval of Patient")
    @ApiResponse(responseCode = "200", description = "Successfully invoice retrieved", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Failure during invoice retrieval", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> getPatient(@PathVariable String ipNumber) throws InvoiceException {
        PatientDTO patient = patientService.getPatient(ipNumber);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
