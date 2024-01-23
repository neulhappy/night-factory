package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Schedules;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Schedules findAllByTheme(Theme theme) {
        return theme.findSchedules(scheduleRepository);
    }

    public Schedule findById(Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("No such schedule");
        } else
            return schedule.get();
    }
}
