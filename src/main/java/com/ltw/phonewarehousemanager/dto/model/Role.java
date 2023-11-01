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
@Table(name = "role", schema = "phonemanagerdb")
public class Role {
    @Id
    @Size(max = 255)
    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Permission> permissions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<User> users = new LinkedHashSet<>();

}