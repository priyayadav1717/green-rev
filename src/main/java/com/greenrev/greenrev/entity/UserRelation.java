package com.greenrev.greenrev.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "family_member_id")
    private String familyMemberId;

    @Column(name = "relation_type")
    private String relationType;

    @Column(name = "is_alert_enabled", nullable = false)
    private Boolean isAlertEnabled = true;
}