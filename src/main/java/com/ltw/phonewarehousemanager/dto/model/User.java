package com.ltw.phonewarehousemanager.dto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Timestamp;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "phonemanagerdb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;
    @Size(max = 100)
    private String name;

    @Size(max = 100)
    @Column(name = "password", length = 100)
    private String password;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Invoice> invoices = new LinkedHashSet<>();
    @Column(name = "is_super_admin")
    private Boolean isSuperAdmin = false;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "created_by", length = 200)
    private String createdBy;

    @Column(name = "modified_by", length = 200)
    private String modifiedBy;

    @Column(name = "modified_time")
    private Timestamp modifiedTime;

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        if (role != null) {
//            role.getPermissions().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionId())));
//            authorities.add(new SimpleGrantedAuthority(role.getRoleId()));
//
//        }
//        return authorities;
//    }

}