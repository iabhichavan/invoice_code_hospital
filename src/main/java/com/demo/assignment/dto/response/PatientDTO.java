package com.demo.assignment.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
public class PatientDTO {
    private int id;
    private String name;
    private String address;
    private String ipNumber;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;
    private List<InvoiceDTO> invoiceDTO;

}
