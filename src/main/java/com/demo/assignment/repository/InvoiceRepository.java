package com.demo.assignment.repository;

import com.demo.assignment.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByInvoiceNumberAndIsDeleted(String invoiceNumber,boolean flag);

    Page<Invoice> findByIsDeleted(boolean flag,Pageable pageable);

    List<Invoice> findByIpNumberAndIsDeleted(String ipNumber, boolean b);
}
