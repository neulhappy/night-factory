package org.recorder.nightfactory.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;


//TODO: 필요한 정보만 getter 직접 만들고 어노테이션 제거
@Getter
public class ReservationPostRequest {
    private LocalDateTime reservationDate;
    private LocalTime startTime;
    private String owner;
    private String roomName;
    private String phoneNumber;
    private int numberOfPeople;
    private int price;
    private LocalDateTime reservationAt;
}
