package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationRequest;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Long save(ReservationRequest dto){

        return reservationRepository.save(Reservation.builder()
                .reservationDate(dto.getReservationDate())
                .reservationName(dto.getReservationName())
                .roomName(dto.getRoomName())
                .phoneNumber(dto.getPhoneNumber())
                .numberOfPeople(dto.getNumberOfPeople())
                .price(dto.getPrice())
                .build()
        ).getId();
    }
}
