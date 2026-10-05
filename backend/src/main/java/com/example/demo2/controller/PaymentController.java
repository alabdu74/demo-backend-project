package com.example.demo2.controller;

import com.example.demo2.service.ArifPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "ArifPay payment integration")
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {

    private final ArifPayService arifPayService;

    @PostMapping("/checkout")
    @Operation(summary = "Create ArifPay checkout session")
    public ResponseEntity<Map<String, Object>> createCheckout(
            @RequestParam String productName,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(arifPayService.createCheckoutSession(productName, amount));
    }

    @GetMapping("/verify/{sessionId}")
    @Operation(summary = "Verify payment status")
    public ResponseEntity<Map<String, Object>> verifyPayment(@PathVariable String sessionId) {
        return ResponseEntity.ok(arifPayService.verifyPayment(sessionId));
    }
}