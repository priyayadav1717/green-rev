package com.greenrev.greenrev.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_logs")
@Data
public class HealthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String familyMemberId;
    private String sellerId;

    @Column(length = 500)
    private String substanceType; // International English Standard Category

    private int frequencyCount;
    private String quantity;
    private String healthStatus;

    @Column(columnDefinition = "TEXT")
    private String aiRecommendation;

    private LocalDateTime loggedAt = LocalDateTime.now();
}