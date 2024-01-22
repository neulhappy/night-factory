package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Schedules;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Schedules findAllByTheme(Theme theme) {
        return theme.findSchedules(scheduleRepository);
    }
}
