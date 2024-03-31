package com.demo.assignment.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO {
    private List<InvoiceDTO> content;
    private int size;
    private int numberOfElements;
    private long totalElements;
    private int totalPages;

}
