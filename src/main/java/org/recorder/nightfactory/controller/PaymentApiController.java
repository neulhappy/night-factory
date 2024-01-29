package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.PortOneApiDTO;
import org.recorder.nightfactory.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments/api")
public class PaymentApiController {
    private final PaymentService paymentService;

    @PostMapping("/complete")
    public ResponseEntity<String> completePayment(@RequestBody PortOneApiDTO.PortOnePaymentResponse portOnePaymentResponse) {
        return null;
    }
}