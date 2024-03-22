package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.PortOneApiDTO;
import org.recorder.nightfactory.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments/api")
public class PaymentApiController {
    private final PaymentService paymentService;

//    @PostMapping("/complete")
//    public ResponseEntity<String> completePayment(@RequestBody PortOneApiDTO.PortOnePaymentResponse portOnePaymentResponse) {
//        return null;
//    }


    @PostMapping("/complete")
    public ResponseEntity<?> completePayment(@RequestParam PortOneApiDTO.PortOnePaymentRequest request) {

        // 결제 성공 가정
        boolean paymentSuccess = true; // 실제 로직에 따라 결정되어야 함

        if (paymentSuccess) {
            return ResponseEntity.ok().body(Map.of("success", true, "message", "결제가 성공적으로 처리되었습니다."));
        } else {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "결제 처리에 실패했습니다."));
        }
    }
}