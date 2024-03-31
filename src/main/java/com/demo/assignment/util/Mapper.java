package com.demo.assignment.util;

import com.demo.assignment.dto.request.InvoiceRequest;
import com.demo.assignment.dto.response.InvoiceDTO;
import com.demo.assignment.dto.response.PageDTO;
import com.demo.assignment.dto.response.PatientDTO;
import com.demo.assignment.entity.Invoice;
import com.demo.assignment.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    public Invoice requestToEntity(InvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setIpNumber(request.getIpNumber());
        invoice.setRoomCharges(request.getRoomCharges());
        invoice.setPharmacy(request.getPharmacy());
        invoice.setMedicalEquipment(request.getMedicalEquipment());
        invoice.setConsultation(request.getConsultation());
        invoice.setConsumable(request.getConsumable());
        invoice.setInvestigations(request.getInvestigations());
        invoice.setBillAmount(totalBillAmount(request));
        return invoice;
    }

    private int totalBillAmount(InvoiceRequest request) {
        int amount;
        amount = request.getRoomCharges() + request.getPharmacy() + request.getMedicalEquipment() +
                request.getConsultation() + request.getConsumable() + request.getInvestigations();
        return amount;
    }

    public InvoiceDTO entityToDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setIpNumber(invoice.getIpNumber());
        invoiceDTO.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDTO.setRoomCharges(invoice.getRoomCharges());
        invoiceDTO.setPharmacy(invoice.getPharmacy());
        invoiceDTO.setMedicalEquipment(invoice.getMedicalEquipment());
        invoiceDTO.setConsultation(invoice.getConsultation());
        invoiceDTO.setConsumable(invoice.getConsumable());
        invoiceDTO.setInvestigations(invoice.getInvestigations());
        invoiceDTO.setBillAmount(invoice.getBillAmount());
        invoiceDTO.setInvoiceDateTime(invoice.getInvoiceDateTime());
        return invoiceDTO;
    }

    public List<InvoiceDTO> convertToDTOList(Page<Invoice> invoices) {
        List<InvoiceDTO> dtoList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceDTO invoiceDTO = entityToDTO(invoice);
            dtoList.add(invoiceDTO);
        }
        return dtoList;
    }
    public List<InvoiceDTO> convertEntityToDTOList(List<Invoice> invoices) {
        List<InvoiceDTO> dtoList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceDTO invoiceDTO = entityToDTO(invoice);
            dtoList.add(invoiceDTO);
        }
        return dtoList;
    }

    public PageDTO mapToPageDTO(Page<Invoice> invoices) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setContent(convertToDTOList(invoices));
        pageDTO.setSize(invoices.getSize());
        pageDTO.setNumberOfElements(invoices.getNumberOfElements());
        pageDTO.setTotalElements(invoices.getTotalElements());
        pageDTO.setTotalPages(invoices.getTotalPages());
        return pageDTO;
    }

    public Invoice updateMapping(Invoice oldInvoice, InvoiceRequest newInvoice) {
        oldInvoice.setIpNumber(newInvoice.getIpNumber() != null ? newInvoice.getIpNumber() : oldInvoice.getIpNumber());
        oldInvoice.setRoomCharges(newInvoice.getRoomCharges());
        oldInvoice.setPharmacy(newInvoice.getPharmacy());
        oldInvoice.setMedicalEquipment(newInvoice.getMedicalEquipment());
        oldInvoice.setConsultation(newInvoice.getConsultation());
        oldInvoice.setConsumable(newInvoice.getConsumable());
        oldInvoice.setInvestigations(newInvoice.getInvestigations());
        oldInvoice.setBillAmount(newInvoice.getBillAmount());
        return oldInvoice;
    }
    public PatientDTO getPatientDTO(Patient patient){
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setAddress(patient.getAddress());
        dto.setIpNumber(patient.getIpNumber());
        dto.setAdmissionDate(patient.getAdmissionDate());
        dto.setDischargeDate(patient.getDischargeDate());
        return dto;
    }

}
