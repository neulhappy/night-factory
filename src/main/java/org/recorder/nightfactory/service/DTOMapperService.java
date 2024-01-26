package org.recorder.nightfactory.service;

import org.recorder.nightfactory.domain.*;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.dto.ScheduleDTO;
import org.recorder.nightfactory.dto.ThemeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DTOMapperService {


    public static ThemeDTO.Themes of(Themes themes) {
        List<ThemeDTO> themeDTOs = new ArrayList<>();
        for (Theme theme : themes.get()) {
            themeDTOs.add(of(theme));
        }
        return new ThemeDTO.Themes(themeDTOs);
    }

    public ThemeDTO.ThemeSet of(ThemeSet themeSet) {
        return new ThemeDTO.ThemeSet(of(themeSet.getTheme()), of(themeSet.getSchedules()));
    }

    public ScheduleDTO of(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(), schedule.getTheme(), schedule.getStartTime(), schedule.getAble());
    }

    public ScheduleDTO.Schedules of(Schedules schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for (Schedule schedule : schedules.get())
            scheduleDTOs.add(of(schedule));

        return new ScheduleDTO.Schedules(scheduleDTOs);
    }

    private static ThemeDTO of(Theme theme) {
        return new ThemeDTO(
                theme.getRoomId()
                , theme.getName()
                , theme.getDescription()
                , theme.getDifficulty()
                , theme.getDifficultyName()
                , theme.getEstimatedTime()
                , theme.getPrice()
                , theme.getGenre().name()
        );
    }
}
