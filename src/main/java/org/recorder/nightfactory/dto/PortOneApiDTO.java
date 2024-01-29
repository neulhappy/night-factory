package org.recorder.nightfactory.dto;

import lombok.Getter;
import org.recorder.nightfactory.domain.PaymentState;

import java.time.LocalDateTime;
import java.util.UUID;

public class PortOneApiDTO {
    public static class PortOneApiTokenResponse {
    }

    public static class PortOneApiTokenRequest {
        private final String key;
        private final String secret;

        public PortOneApiTokenRequest(String key, String secret) {
            this.key = key;
            this.secret = secret;
        }
    }

    @Getter
    public static class PortOnePaymentRequest {
        private String storeId;
        private String channelKey;

        private UUID paymentId;
        private String orderName;
        private Long totalAmount;
        public String fullname;
        public String phoneNumber;

    }

    public static class PortOnePaymentResponse {           // PortOne 결제 모듈의 결제 요청에 대한 반환값
        boolean success;
        String impUid;                              //PortOne에서 채번한 id
        String merchantUid;                         //어플리케이션에서 채번한 id(prefix + UUID)
        long paidAmount;
        String currency;
        PaymentState status;
        LocalDateTime paidAt;
        String receiptUrl;
    }


}
