package com.greenrev.greenrev.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "civic_issues")
@Data
public class CivicIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String citizenId;

    @Column(length = 500)
    private String category;

    @Column(length = 500)
    private String locationAddress;

    private String severityLevel;

    @Column(name = "is_anonymous")
    private Boolean anonymous = true;

    private String status = "PENDING";

    @Column(length = 1000)
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String aiActionPlan;

    private LocalDateTime createdAt = LocalDateTime.now();
}