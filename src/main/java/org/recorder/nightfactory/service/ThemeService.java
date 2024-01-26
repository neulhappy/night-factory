package org.recorder.nightfactory.service;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;


    public ThemeDTO.ThemeListResponse themeList() {
//        return new ThemeDTO.ThemeListResponse(org.recorder.nightfactory.domain.Themes.listTheme(themeRepository));
        return null;
    }
}
