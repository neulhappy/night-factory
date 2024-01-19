package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Long save(ReservationPostRequest dto){

        return reservationRepository.save(Reservation.builder()
                .reservationDate(dto.getReservationDate())
//                .startTime(dto.getStartTime())
                .owner(dto.getOwner())
                .roomName(dto.getRoomName())
                .phoneNumber(dto.getPhoneNumber())
                .numberOfPeople(dto.getNumberOfPeople())
                .price(dto.getPrice())
                .build()
        ).getId();
    }
}
