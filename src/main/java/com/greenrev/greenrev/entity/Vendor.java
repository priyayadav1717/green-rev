package com.greenrev.greenrev.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sellerId;        // Unique Vendor ID (e.g., "VEND-LKO-001")
    private String shopName;        // Store name
    private String shopAddress;     // Location (e.g., "Matiyari, Lucknow")
    private String licenseNumber;   // Mandatory Civic Retail License

    @Column(name = "is_verified")
    private Boolean isVerified = true; // boolean se Boolean kar diya
}