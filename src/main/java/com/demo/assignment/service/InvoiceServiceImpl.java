package com.demo.assignment.service;

import com.demo.assignment.dto.request.InvoiceRequest;
import com.demo.assignment.dto.response.InvoiceDTO;
import com.demo.assignment.dto.response.PageDTO;
import com.demo.assignment.entity.Invoice;
import com.demo.assignment.entity.Patient;
import com.demo.assignment.exception.InvoiceException;
import com.demo.assignment.repository.InvoiceRepository;
import com.demo.assignment.repository.PatientRepository;
import com.demo.assignment.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private final Mapper mapper;
    private final InvoiceRepository repository;
    private final PatientRepository patientRepository;

    public InvoiceServiceImpl(Mapper mapper, InvoiceRepository repository, PatientRepository patientRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.patientRepository = patientRepository;
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceRequest request) throws BadRequestException {
        log.info("InvoiceServiceImpl::createInvoice::START");
        Optional<Patient> byIpNumber = patientRepository.findByIpNumber(request.getIpNumber());
        if (byIpNumber.isPresent()) {
            Patient patient = byIpNumber.get();
            patient.setDischargeDate(LocalDateTime.now());
            patientRepository.save(patient);
            Invoice invoice = mapper.requestToEntity(request);
            invoice.setInvoiceDateTime(LocalDateTime.now());
            Invoice save = repository.save(invoice);
            save.setInvoiceNumber(generateInvoiceNumber(save.getId()));
            Invoice invoice1 = repository.save(invoice);
            log.info("InvoiceServiceImpl::createInvoice::END");
            return mapper.entityToDTO(invoice1);
        } else {
            log.error("InvoiceServiceImpl::createInvoice::Exception::request::{}",request);
            throw new BadRequestException("IP number is invalid");
        }
    }

    @Override
    public PageDTO retrieveAllInvoice(Pageable pageable) throws InvoiceException {
        log.info("InvoiceServiceImpl::retrieveAllInvoice::START");
        Page<Invoice> invoicePage = repository.findByIsDeleted(false,pageable);
        if (!invoicePage.isEmpty()) {
            log.info("InvoiceServiceImpl::retrieveAllInvoice::END");
            return mapper.mapToPageDTO(invoicePage);
        } else {
            throw new InvoiceException("No invoice found");
        }
    }

    @Override
    public InvoiceDTO retrieveInvoice(String invoiceNumber) throws InvoiceException {
        log.info("InvoiceServiceImpl::retrieveInvoice::START");
        Optional<Invoice> optionalInvoice = repository.findByInvoiceNumberAndIsDeleted(invoiceNumber,false);
        if (optionalInvoice.isPresent()) {
            log.info("InvoiceServiceImpl::retrieveInvoice::END");
            return mapper.entityToDTO(optionalInvoice.get());
        } else {
            log.error("InvoiceServiceImpl::retrieveInvoice::Exception::request::{}",invoiceNumber);
            throw new InvoiceException("No invoice found with given number " + invoiceNumber);
        }
    }

    @Override
    public InvoiceDTO updateInvoice(int id, InvoiceRequest request) throws InvoiceException {
        log.info("InvoiceServiceImpl::updateInvoice::START");
        Optional<Invoice> optionalInvoice = repository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = mapper.updateMapping(optionalInvoice.get(), request);
            repository.save(invoice);
            log.info("InvoiceServiceImpl::updateInvoice::END");
            return mapper.entityToDTO(invoice);
        } else {
            log.error("InvoiceServiceImpl::updateInvoice::Exception::ID::{}::request::{}",id,request);
            throw new InvoiceException("No invoice found with given id " + id);
        }
    }

    @Override
    public InvoiceDTO deleteInvoice(int id) throws InvoiceException {
        log.info("InvoiceServiceImpl::deleteInvoice::START");
        Optional<Invoice> optionalInvoice = repository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setDeleted(true);
            repository.save(invoice);
            log.info("InvoiceServiceImpl::deleteInvoice::END");
            return mapper.entityToDTO(invoice);
        } else {
            log.error("InvoiceServiceImpl::deleteInvoice::Exception::request::{}",id);
            throw new InvoiceException("No invoice found with given id " + id);
        }
    }

    private String generateInvoiceNumber(int id) {
        return "IN0" + id;
    }
}
