package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.NurigoService;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.recorder.nightfactory.domain.PaymentState.*;


@RequiredArgsConstructor
@Service

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;
    private final NurigoService nurigoService;

    private Reservation currentReservation;


    public List<ReservationDTO> getReservationsByDate(LocalDate date) {
        // 해당 날짜에 대한 모든 스케줄을 가져옵니다.
        List<Schedule> schedules = scheduleRepository.findAllByDate(date);

        // 스케줄과 관련된 예약 정보를 가져와 DTO로 변환합니다.
        return schedules.stream().map(schedule -> {
            Reservation reservation = reservationRepository.findBySchedule(schedule);
            return new ReservationDTO(reservation);
        }).collect(Collectors.toList());
    }
    //예약 저장
    public ReservationDTO.RegisterResponse make(ReservationDTO.RegisterRequest request) {
        Reservation reservation = new Reservation();
        reservation.setAmount(request.getAmount());
        reservation.setReservationDate(request.getReservationDate());
        reservation.setState(READY);

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(request.getScheduleId());
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            reservation.setSchedule(schedule);
            reservationRepository.save(reservation);
            currentReservation = reservation;
        }

        return new ReservationDTO.RegisterResponse();
    }

    public ReservationDTO.RegisterResponse paying(ReservationDTO.RegisterRequest request) {

        if (currentReservation != null) { // 클래스 필드에 예약 객체가 있는지 확인
            currentReservation.setOwner(request.getOwner());
            currentReservation.setPhoneNumber(request.getPhoneNumber());
            currentReservation.setNumberOfPeople(request.getNumberOfPeople());

            // nurigoService.sendSMS();
            reservationRepository.save(currentReservation);
        } else{
            return new ReservationDTO.RegisterResponse();
        }

        return new ReservationDTO.RegisterResponse();
    }


    public ReservationDTO.RegisterResponse paymentSave(ReservationDTO.RegisterRequest request) {
        currentReservation.setPaidAt(LocalDateTime.now());
        currentReservation.setState(PAID);
        currentReservation.setAmount(request.getAmount());
            reservationRepository.save(currentReservation);

//        nurigoService.sendSMS();



        return new ReservationDTO.RegisterResponse();
    }


    //예약 취소
    public ReservationDTO.DeleteResponse delete(ReservationDTO.DeleteRequest request) {
//        reservationRepository.delete(getReservation(requestDto));
        return new ReservationDTO.DeleteResponse();
    }

}
