package com.example.demo2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArifPayService {

    @Value("${arifpay.api-key}")
    private String apiKey;

    @Value("${arifpay.checkout-url}")
    private String checkoutUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> createCheckoutSession(String productName, BigDecimal amount) {
        log.info("Creating ArifPay checkout session for product: {}, amount: {}", productName, amount);

        Map<String, Object> request = new HashMap<>();
        request.put("amount", amount);
        request.put("productName", productName);
        request.put("currency", "ETB");

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("sessionId", "demo-session-" + System.currentTimeMillis());
            response.put("checkoutUrl", checkoutUrl + "/session/" + System.currentTimeMillis());
            response.put("status", "CREATED");
            response.put("amount", amount);

            log.info("Checkout session created: {}", response);
            return response;

        } catch (Exception e) {
            log.error("Failed to create checkout session: {}", e.getMessage());
            throw new RuntimeException("Payment checkout failed");
        }
    }

    public Map<String, Object> verifyPayment(String sessionId) {
        log.info("Verifying payment for session: {}", sessionId);

        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("status", "PAID");
        response.put("message", "Payment verified successfully");

        return response;
    }
}