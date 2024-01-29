package org.recorder.nightfactory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.domain.PaymentState;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ReservationDTO {
    UUID id;
    String owner;
    int numberOfPeople;
    String phoneNumber;
    long amount;
    PaymentState state;

    @Getter
    @NoArgsConstructor
    public static class GetRequest {
        UUID id;
        String owner;
        String phoneNumber;
    }

    @Getter
    @NoArgsConstructor
    public static class GetResponse {
        String themeName;
        LocalTime time;
        ReservationDTO reservation;

    }


    @NoArgsConstructor
    @Getter
    public static class RegisterRequest {
        private int scheduleId;
        private Date reservationDate;
        private String owner;
        private String phoneNumber;
        private int numberOfPeople;
        private LocalDateTime reservationAt;
    }


    @NoArgsConstructor
    @Getter
    public static class RegisterResponse {
        PortOneApiDTO.PortOnePaymentRequest request;
        Long price;
    }

    @NoArgsConstructor
    @Getter
    public static class PaymentCompletionApiRequest {
        PortOneApiDTO.PortOnePaymentResponse paymentResponse;

    }

    @NoArgsConstructor
    @Getter
    public static class PaymentCompletionApiResponse {
        boolean isComplete;
        ReservationDTO reservation;
    }

    @NoArgsConstructor
    @Getter
    public static class DeleteRequest {

    }


    public static class DeleteResponse {

    }


    @Getter
    @NoArgsConstructor
    public static class AllSchduleResponse {
        List<ThemeDTO.ThemeSet> allSchedules;


        public AllSchduleResponse(List<ThemeDTO.ThemeSet> allSchedules) {
            this.allSchedules = allSchedules;
        }
    }
}
