package com.greenrev.greenrev.controller;

import com.greenrev.greenrev.entity.UserRelation;
import com.greenrev.greenrev.repository.UserRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relations")
@CrossOrigin(origins = "*")
public class UserRelationController {

    @Autowired
    private UserRelationRepository userRelationRepository;

    // Link user with family member / guardian
    @PostMapping("/link")
    public ResponseEntity<UserRelation> linkFamilyMember(@RequestBody UserRelation relation) {
        // Ensure default values if null to prevent PropertyValueException
        if (relation.getIsAlertEnabled() == null) {
            relation.setIsAlertEnabled(true);
        }
        if (relation.getRelationType() == null) {
            relation.setRelationType("Guardian");
        }

        UserRelation savedRelation = userRelationRepository.save(relation);
        return ResponseEntity.ok(savedRelation);
    }

    // Get family members linked to a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRelation>> getFamilyForUser(@PathVariable String userId) {
        return ResponseEntity.ok(userRelationRepository.findByUserId(userId));
    }

    // Get users monitored by a family member
    @GetMapping("/family/{familyMemberId}")
    public ResponseEntity<List<UserRelation>> getUsersForFamilyMember(@PathVariable String familyMemberId) {
        return ResponseEntity.ok(userRelationRepository.findByFamilyMemberId(familyMemberId));
    }
}