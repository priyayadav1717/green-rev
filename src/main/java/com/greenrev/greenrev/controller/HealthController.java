package com.greenrev.greenrev.controller;

import com.greenrev.greenrev.entity.HealthLog;
import com.greenrev.greenrev.repository.HealthLogRepository;
import com.greenrev.greenrev.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @Autowired
    private HealthLogRepository healthLogRepository;

    @Autowired
    private GeminiService geminiService;

    // 1. Vendor Rapid Log Entry
    @PostMapping("/vendor-scan")
    public ResponseEntity<HealthLog> logSubstancePurchase(@RequestBody HealthLog healthLog) {
        String aiPrompt = "Provide a 1-sentence health alert recommendation for high intake of substance: "
                + healthLog.getSubstanceType() + " frequency: " + healthLog.getFrequencyCount();

        try {
            String aiRecommendation = geminiService.getAiResponse(aiPrompt);
            healthLog.setAiRecommendation(aiRecommendation);
        } catch (Exception e) {
            healthLog.setAiRecommendation("High consumption frequency detected. Guardian alert activated.");
        }

        HealthLog savedLog = healthLogRepository.save(healthLog);
        return ResponseEntity.ok(savedLog);
    }

    // 2. Search User Health Ledger Profile
    @GetMapping("/profile/{userId}")
    public ResponseEntity<String> getUserHealthProfile(@PathVariable String userId) {
        List<HealthLog> userLogs = healthLogRepository.findByUserId(userId);

        if (userLogs.isEmpty()) {
            return ResponseEntity.ok("User ID: " + userId + " has no recorded substance intake logs. Health status: Optimal.");
        }

        int totalCount = userLogs.stream().mapToInt(HealthLog::getFrequencyCount).sum();
        return ResponseEntity.ok("User ID: " + userId + " has " + userLogs.size() + " log entries with total frequency count of "
                + totalCount + ". RevAI Risk Score: Critical. Guardian wellness alert active.");
    }
}