package com.demo.assignment.controller;

import com.demo.assignment.dto.request.InvoiceRequest;
import com.demo.assignment.dto.response.InvoiceDTO;
import com.demo.assignment.dto.response.PageDTO;
import com.demo.assignment.exception.InvoiceException;
import com.demo.assignment.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/invoice/v1")
@Tag(name = "Invoice controller", description = "All the operation related to invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    @Operation(summary = "Creation of invoice")
    @ApiResponse(responseCode = "200", description = "Successfully invoice created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Failure during invoice creation", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> createInvoice(@RequestBody InvoiceRequest request) throws BadRequestException {
        InvoiceDTO invoice = invoiceService.createInvoice(request);
        log.info("InvoiceController::createInvoice::SUCCESS::statusCode::{}",HttpStatus.OK);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Retrieval of invoice page")
    @ApiResponse(responseCode = "200", description = "Successfully invoice retrieved", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Failure during invoice retrieval", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> retrieveAllInvoice(Pageable pageable) throws InvoiceException {
        PageDTO pageDTO = invoiceService.retrieveAllInvoice(pageable);
        log.info("InvoiceController::retrieveAllInvoice::SUCCESS::statusCode::{}",HttpStatus.OK);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @GetMapping("/{invoiceNumber}")
    @Operation(summary = "Retrieval of invoice by invoice number")
    @ApiResponse(responseCode = "200", description = "Successfully invoice retrieved", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Failure during invoice retrieval", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> retrieveInvoice(@PathVariable String invoiceNumber) throws InvoiceException {
        InvoiceDTO invoice = invoiceService.retrieveInvoice(invoiceNumber);
        log.info("InvoiceController::retrieveInvoice::SUCCESS::statusCode::{}",HttpStatus.OK);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update of invoice by invoice id")
    @ApiResponse(responseCode = "200", description = "Successfully invoice updated", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Failure during invoice update", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> updateInvoice(@PathVariable int id, @RequestBody InvoiceRequest request) throws InvoiceException {
        InvoiceDTO invoice = invoiceService.updateInvoice(id, request);
        log.info("InvoiceController::updateInvoice::SUCCESS::statusCode::{}",HttpStatus.OK);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletion of invoice by invoice id")
    @ApiResponse(responseCode = "200", description = "Successfully invoice deleted", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Failure during invoice deletion", content = @Content(mediaType = "application/json"))
    public ResponseEntity<Object> deleteInvoice(@PathVariable int id) throws InvoiceException {
        InvoiceDTO invoice = invoiceService.deleteInvoice(id);
        log.info("InvoiceController::deleteInvoice::SUCCESS::statusCode::{}",HttpStatus.OK);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
}
