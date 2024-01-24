package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReservationDetailService {

    private final ReservationRepository reservationRepository;
    //예약자 이름으로 예약 정보 가져오기
    public List<Reservation> findByOwner(String owner){
        return reservationRepository.findByOwnerOrderByReservationDateDesc(owner);
    }

}
