package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.PortOneApiDTO;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {
    PaymentRepository paymentRepository;
    private final String API_URL = "https://api.iamport.kr";


    public ReservationDTO.PaymentCompletionApiResponse complete(ReservationDTO.PaymentCompletionApiRequest request) {

        return null;
    }


    public PortOneApiDTO.PortOneApiTokenResponse getPortOneApiToken(@Value("${api.portOne.apiKey}") String apiKey,
                                                                    @Value("${api.portOne.apiSecret}") String apiSecret) {
        String url = API_URL + "/users/getToken";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PortOneApiDTO.PortOneApiTokenRequest request = new PortOneApiDTO.PortOneApiTokenRequest(apiKey, apiSecret);
        HttpEntity<PortOneApiDTO.PortOneApiTokenRequest> requestEntity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PortOneApiDTO.PortOneApiTokenResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                PortOneApiDTO.PortOneApiTokenResponse.class
        );
        return responseEntity.getBody();
    }
}