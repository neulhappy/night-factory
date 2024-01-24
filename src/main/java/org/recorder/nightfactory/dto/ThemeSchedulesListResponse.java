package org.recorder.nightfactory.dto;


import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Schedules;
import org.recorder.nightfactory.domain.Theme;

import java.util.List;

public class ThemeSchedulesListResponse {
    Theme theme;
    Schedules schedules;

    public ThemeSchedulesListResponse(Theme theme, Schedules schedules) {
        this.theme = theme;
        this.schedules = schedules;
    }

    public Theme getThemes() {
        return theme;
    }

    public List<Schedule> getSchedules() {
        return schedules.getSchedules();
    }

}
