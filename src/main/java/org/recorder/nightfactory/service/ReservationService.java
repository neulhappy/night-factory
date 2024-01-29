package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;


    //예약 저장
    public ReservationDTO.RegisterResponse save(ReservationDTO.RegisterRequest request) {
//        Reservation reservation = Reservation;
//        reservationRepository.save(reservation);
        return new ReservationDTO.RegisterResponse();
    }

    //예약 취소
    public ReservationDTO.DeleteResponse delete(ReservationDTO.DeleteRequest request) {
//        reservationRepository.delete(getReservation(requestDto));
        return new ReservationDTO.DeleteResponse();
    }

}
