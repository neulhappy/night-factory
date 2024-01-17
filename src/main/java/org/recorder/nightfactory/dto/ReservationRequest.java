package org.recorder.nightfactory.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ReservationRequest {
    private LocalDateTime reservationDate;
    private String startTime;
    private String owner;
    private String roomName;
    private String phoneNumber;
    private int numberOfPeople;
    private int price;
    private LocalDateTime reservationAt;
}
