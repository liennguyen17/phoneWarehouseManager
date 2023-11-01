package com.ltw.phonewarehousemanager.repository.invoiceDetail;

import com.ltw.phonewarehousemanager.dto.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long>, JpaSpecificationExecutor<InvoiceDetail> {
    Optional<InvoiceDetail> findById(Long id);
}
