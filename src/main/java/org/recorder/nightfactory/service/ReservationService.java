package org.recorder.nightfactory.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.dto.ReservationPostResponse;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleService scheduleService;

    public ReservationPostResponse save(ReservationPostRequest requestDto){//TODO: getPrice부분 확인
        return new ReservationPostResponse(reservationRepository.save(getReservation(requestDto)), getPrice(requestDto.getScheduleId()));
    }

    public Long getPrice(Integer id){
        Schedule schedule = getSchedule(id);
        Theme theme = getTheme(schedule);
        return theme.getPrice();
    }

    private static Theme getTheme(Schedule schedule) {
        return schedule.getTheme();
    }

    private Schedule getSchedule(Integer id) {
        return scheduleService.findById(id);
    }

    private Reservation getReservation(ReservationPostRequest requestDto) {
        return new Reservation(
               scheduleService.findById(requestDto.getScheduleId())
                , requestDto.getOwner()
                , requestDto.getReservationDate()
                , requestDto.getPhoneNumber()
                , requestDto.getNumberOfPeople()
        );
    }

}
