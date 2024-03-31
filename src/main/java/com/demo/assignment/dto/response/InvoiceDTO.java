package com.demo.assignment.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InvoiceDTO {
    int id;
    private String invoiceNumber;
    private String ipNumber;
    private int roomCharges;
    private int pharmacy;
    private int medicalEquipment;
    private int consultation;
    private int consumable;
    private int investigations;
    private long billAmount;
    private LocalDateTime invoiceDateTime;
}
