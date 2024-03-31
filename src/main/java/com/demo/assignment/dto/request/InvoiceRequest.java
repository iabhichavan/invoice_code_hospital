package com.demo.assignment.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceRequest {
    private String ipNumber;
    private int roomCharges;
    private int pharmacy;
    private int medicalEquipment;
    private int consultation;
    private int consumable;
    private int investigations;
    private long billAmount;
}
