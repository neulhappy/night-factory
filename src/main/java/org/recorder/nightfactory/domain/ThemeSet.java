package org.recorder.nightfactory.domain;

import org.recorder.nightfactory.repository.ScheduleRepository;

import java.util.List;

public class ThemeSet {
    private Theme theme;
    private Schedules schedules;

    private ThemeSet(Theme theme, Schedules schedules) {
        this.theme = theme;
        this.schedules = schedules;
    }

    public static ThemeSet make(Theme theme, ScheduleRepository repository) {
        return new ThemeSet(theme, theme.findSchedules(repository));
    }

    public Theme getTheme() {
        return theme;
    }

    public List<Schedule> getSchedules() {
        return schedules.getSchedules();
    }
}
