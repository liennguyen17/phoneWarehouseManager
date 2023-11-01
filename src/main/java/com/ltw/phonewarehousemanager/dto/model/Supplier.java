package com.ltw.phonewarehousemanager.dto.model;

import jakarta.persistence.*;
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
@Table(name = "suppliers", schema = "phonemanagerdb")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 255)
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_by", length = 200)
    private String createdBy;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "modified_by", length = 200)
    private String modifiedBy;

    @Column(name = "modified_time")
    private Timestamp modifiedTime;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> products;

}