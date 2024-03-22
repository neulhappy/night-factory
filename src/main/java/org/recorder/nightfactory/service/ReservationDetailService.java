package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationDetailService {

    private final ReservationRepository reservationRepository;

    //예약자 이름으로 예약 정보 가져오기
    public ReservationDTO.GetResponse findByOwner(ReservationDTO.GetRequest request){

        reservationRepository.findByOwnerOrderByReservationDateDesc(request.getOwner());

        return new ReservationDTO.GetResponse();
    }


//    public Reservation reservationFindByOwner(ReservationDTO.GetRequest request){
//        Reservation reservation = reservationRepository.findByOwner(request.getOwner());
//
//
//        return reservation;
//    }

    public Reservation findByReservationId(String reservationId, String owner, String phoneNumber) {
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            String reservationSecondGroup = reservation.getId().toString().substring(9, 13);
            if (reservationId.equals(reservationSecondGroup) && owner.equals(reservation.getOwner()) && phoneNumber.equals(reservation.getPhoneNumber()) ) {
                return reservation;
            }
        }

        return null;
    }

    public Reservation findByReservationAt(LocalDateTime reservationAt){
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            LocalDateTime reservationTime = reservation.getReservationAt();
            if (reservationAt.equals(reservationTime)) {
                return reservation;
            }
        }
        return null;
    }
}

