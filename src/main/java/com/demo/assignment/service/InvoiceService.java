package com.demo.assignment.service;

import com.demo.assignment.dto.request.InvoiceRequest;
import com.demo.assignment.dto.response.InvoiceDTO;
import com.demo.assignment.dto.response.PageDTO;
import com.demo.assignment.exception.InvoiceException;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    InvoiceDTO createInvoice(InvoiceRequest request) throws BadRequestException;

    PageDTO retrieveAllInvoice(Pageable pageable) throws InvoiceException;

    InvoiceDTO retrieveInvoice(String invoiceNumber) throws InvoiceException;

    InvoiceDTO updateInvoice(int id, InvoiceRequest request) throws InvoiceException;

    InvoiceDTO deleteInvoice(int id) throws InvoiceException;
}
