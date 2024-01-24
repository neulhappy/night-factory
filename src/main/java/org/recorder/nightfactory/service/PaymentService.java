package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.PortOneApiTokenResponse;
import org.recorder.nightfactory.dto.PortOnePaymentResponse;
import org.recorder.nightfactory.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    PaymentRepository paymentRepository;


    public PortOneApiTokenResponse getPortOneApiToken(@Value("${api.portOne.apiSecret}") String secret) {
        return new PortOneApiTokenResponse();
    }
}
