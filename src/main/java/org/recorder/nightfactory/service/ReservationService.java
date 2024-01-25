package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.dto.ReservationPostResponse;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;



@RequiredArgsConstructor
@Service

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleService scheduleService;


    //예약 저장
    public ReservationPostResponse save(ReservationPostRequest requestDto){//TODO: getPrice부분 확인
        return new ReservationPostResponse(reservationRepository.save(getReservation(requestDto)), getPrice(requestDto.getScheduleId()));
    }

    //예약 취소
    public void delete(ReservationPostRequest requestDto){
        reservationRepository.delete(getReservation(requestDto));
    }




    public Long getPrice(Integer id){
        Schedule schedule = getSchedule(id);
        Theme theme = getTheme(schedule);
        return theme.getPrice();
    }

    public Theme getTheme(Schedule schedule) {
        return schedule.getTheme();
    }

    public Schedule getSchedule(Integer id) {
        return scheduleService.findById(id);
    }

    public Reservation findById(UUID id){
        return reservationRepository.getById(id)
                .orElseThrow(() -> new NoSuchElementException("예약번호 : " + id + "에 맞는 예약 정보가 없습니다."));
    }

    public Reservation getReservation(ReservationPostRequest requestDto) {
        return new Reservation(
               scheduleService.findById(requestDto.getScheduleId())
                , requestDto.getOwner()
                , requestDto.getReservationDate()
                , requestDto.getPhoneNumber()
                , requestDto.getNumberOfPeople()
        );
    }

}
