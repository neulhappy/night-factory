package org.recorder.nightfactory.dto;

import org.recorder.nightfactory.domain.PaymentState;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public class ReservationPostResponse {

    private UUID id;
    private int schedule_id;
    private String owner;
    private Date reservationDate;
    private String phoneNumber;
    private Integer numberOfPeople;
    private LocalDateTime reservationAt;
    private UUID paymentId;
    private String impUid;
    private Long amount;
    private PaymentState state;
    LocalDateTime paidAt;
    Long price;


    public Long getPrice() {
        return price;
    }
}
