package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.ThemeSets;
import org.recorder.nightfactory.dto.ThemeSchedulesListResponse;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;

    public ThemeSchedulesListResponse findAllByDate(Date date) {
        ThemeSets themeSets = ThemeSets.findAll(themeRepository, scheduleRepository);
        themeSets.queryAllScheduleAble(reservationRepository, date);
        return new ThemeSchedulesListResponse(themeSets);
    }

    public Schedule findById(Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("No such schedule");
        } else
            return schedule.get();
    }
}
