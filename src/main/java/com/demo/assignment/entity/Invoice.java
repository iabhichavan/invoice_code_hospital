package com.demo.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ipNumber;
    private String invoiceNumber;
    private int roomCharges;
    private int pharmacy;
    private int medicalEquipment;
    private int consultation;
    private int consumable;
    private int investigations;
    private long billAmount;
    private LocalDateTime invoiceDateTime;
    private boolean isDeleted;

}
