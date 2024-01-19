package org.recorder.nightfactory.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
public class ReservationRequest {
    private LocalDateTime reservationDate;
    private LocalTime startTime;
    private String owner;
    private String roomName;
    private String phoneNumber;
    private int numberOfPeople;
    private int price;
    private LocalDateTime reservationAt;
}
