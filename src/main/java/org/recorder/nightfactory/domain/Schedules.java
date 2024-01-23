package org.recorder.nightfactory.domain;

import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;

import java.util.List;

public class Schedules {
    private final List<Schedule> schedules;

    private Schedules(ScheduleRepository scheduleRepository, Theme theme) {
        this.schedules = scheduleRepository.findAllByThemeOrderByStartTimeAsc(theme);
    }

    public static Schedules ListSchedules(ScheduleRepository scheduleRepository, Theme theme) {
        return new Schedules(scheduleRepository, theme);
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
