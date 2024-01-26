package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ReservationDetailService {

    private final ReservationRepository reservationRepository;
    //예약자 이름으로 예약 정보 가져오기
    public ReservationDTO.GetResponse findByOwner(ReservationDTO.GetRequest request){

        return new ReservationDTO.GetResponse();
    }

}
