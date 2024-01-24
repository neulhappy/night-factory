package org.recorder.nightfactory.service;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.ThemeSets;
import org.recorder.nightfactory.dto.ThemeSchedulesListResponse;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;

    public ThemeSchedulesListResponse themeSchedulesList() {
        return new ThemeSchedulesListResponse(ThemeSets.findAll(themeRepository, scheduleRepository));
    }
}
