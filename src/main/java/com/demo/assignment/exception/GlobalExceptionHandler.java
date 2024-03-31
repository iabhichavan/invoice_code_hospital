package com.demo.assignment.exception;

import com.demo.assignment.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerForBadRequest(BadRequestException exception) {
        log.error("GlobalExceptionHandler::handlerForBadRequest::Exception::{}",getStackTrace(exception));
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvoiceException.class)
    public ResponseEntity<ErrorResponse> handlerForInvoiceException(InvoiceException exception) {
        log.error("GlobalExceptionHandler::handlerForInvoiceException::Exception::{}",getStackTrace(exception));
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerForOtherExceptions(Exception exception) {
        log.error("GlobalExceptionHandler::handlerForOtherExceptions::Exception::{}",getStackTrace(exception));
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
