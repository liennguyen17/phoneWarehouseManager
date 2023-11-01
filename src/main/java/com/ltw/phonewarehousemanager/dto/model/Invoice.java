package com.ltw.phonewarehousemanager.dto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name = "invoices", schema = "phonemanagerdb")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceDetail> invoiceDetails = new LinkedHashSet<>();
    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;
    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "created_by", length = 200)
    private String createdBy;

}