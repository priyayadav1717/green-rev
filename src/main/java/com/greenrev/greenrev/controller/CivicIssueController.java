package com.greenrev.greenrev.controller;

import com.greenrev.greenrev.entity.CivicIssue;
import com.greenrev.greenrev.repository.CivicIssueRepository;
import com.greenrev.greenrev.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/civic")
@CrossOrigin(origins = "*") // Allows Frontend index.html connection
public class CivicIssueController {

    @Autowired
    private CivicIssueRepository civicIssueRepository;

    @Autowired
    private GeminiService geminiService;

    // 1. Fetch All Civic Issues (For live charts & analytics)
    @GetMapping("/all")
    public ResponseEntity<List<CivicIssue>> getAllIssues() {
        return ResponseEntity.ok(civicIssueRepository.findAll());
    }

    // 2. Submit New Civic Report (Triggers RevAI Action)
    @PostMapping("/report")
    public ResponseEntity<CivicIssue> submitReport(@RequestBody CivicIssue issue) {
        try {
            // Ensure default values if null
            if (issue.getCategory() == null) issue.setCategory("General Civic Issue");
            if (issue.getLocationAddress() == null) issue.setLocationAddress("Lane-42, Sector-7");
            if (issue.getSeverityLevel() == null) issue.setSeverityLevel("MEDIUM");

            String aiPrompt = "Generate a 2-sentence municipal action directive for civic hazard: "
                    + issue.getCategory() + " at location: " + issue.getLocationAddress()
                    + " with severity: " + issue.getSeverityLevel();

            String aiResponse = geminiService.getAiResponse(aiPrompt);
            issue.setAiActionPlan(aiResponse);
        } catch (Exception e) {
            issue.setAiActionPlan("RevAI Protocol Issued: Automated dispatch unit assigned for immediate inspection.");
        }

        CivicIssue savedIssue = civicIssueRepository.save(issue);
        return ResponseEntity.ok(savedIssue);
    }
}