package com.ltw.phonewarehousemanager.dto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock_out_details", schema = "phonemanagerdb")
public class StockOutDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity_out")
    private Long quantityOut;

    @Column(name = "price",precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_out_date")
    private Date stockOutDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "created_by", length = 200)
    private String createdBy;


}