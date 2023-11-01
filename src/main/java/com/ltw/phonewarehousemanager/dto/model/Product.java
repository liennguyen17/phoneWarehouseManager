package com.ltw.phonewarehousemanager.dto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "phonemanagerdb")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private Set<InvoiceDetail> invoiceDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<StockOutDetail> stockOutDetails = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "warehouse_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "warehouse_id"))
    private Set<Warehouse> warehouses = new LinkedHashSet<>();

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "created_by", length = 200)
    private String createdBy;

    @Column(name = "modified_by", length = 200)
    private String modifiedBy;

    @Column(name = "modified_time")
    private Timestamp modifiedTime;

}