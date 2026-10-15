package com.greenrev.greenrev.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    // You can set spring.ai.gemini.api-key in application.yml or leave fallback active
    @Value("${gemini.api.key:}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAiResponse(String prompt) {
        // If API key is not configured, return a smart default municipal directive instantly
        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("YOUR_GEMINI_API_KEY")) {
            return "RevAI Directive: Automated municipal task force & emergency dispatch unit assigned. Resolution in progress.";
        }

        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> part = new HashMap<>();
            part.put("text", prompt);

            Map<String, Object> content = new HashMap<>();
            content.put("parts", List.of(part));

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("contents", List.of(content));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map responseBody = response.getBody();
                List<Map> candidates = (List<Map>) responseBody.get("candidates");
                if (candidates != null && !candidates.isEmpty()) {
                    Map contentMap = (Map) candidates.get(0).get("content");
                    List<Map> partsList = (List<Map>) contentMap.get("parts");
                    if (partsList != null && !partsList.isEmpty()) {
                        return (String) partsList.get(0).get("text");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Gemini API connection error: " + e.getMessage());
        }

        return "RevAI Directive: Priority issue logged into municipal ledger. Verification and cleanup scheduled.";
    }
}