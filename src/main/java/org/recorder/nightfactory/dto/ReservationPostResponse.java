package org.recorder.nightfactory.dto;

import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Theme;

import java.time.LocalTime;

public class ReservationPostResponse {

    Reservation reservation;

    Long price;


    public ReservationPostResponse(Reservation reservation, Long price) {
        this.reservation = reservation;
        this.price = reservation.getSchedule().getTheme().getPrice();
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Long getPrice() {
        return price;
    }
}
