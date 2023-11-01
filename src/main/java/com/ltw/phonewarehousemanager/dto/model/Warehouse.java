package com.ltw.phonewarehousemanager.dto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouses", schema = "phonemanagerdb")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "warehouses")
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "warehouse")
    private Set<StockInDetail> stockInDetails;


}