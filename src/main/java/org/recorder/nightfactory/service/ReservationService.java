package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;


@RequiredArgsConstructor
@Service

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;


    //예약 저장
    public ReservationDTO.PostResponse save(ReservationDTO.PostRequest request) {
//        Reservation reservation = Reservation;
//        reservationRepository.save(reservation);
        return new ReservationDTO.PostResponse();
    }

    //예약 취소
    public ReservationDTO.DeleteResponse delete(ReservationDTO.DeleteRequest request) {
//        reservationRepository.delete(getReservation(requestDto));
        return new ReservationDTO.DeleteResponse();
    }

}
