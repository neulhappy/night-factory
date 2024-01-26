package org.recorder.nightfactory.service;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.ThemeSet;
import org.recorder.nightfactory.domain.Themes;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;
    private final DTOMapperService dtoMapperService;

    public ThemeDTO.ThemeListResponse themeList() {
        Themes themes = Themes.listTheme(themeRepository);
        //of() 호출에서 static context로 취급 되고 있음. 원인 불명이나 작동은 가능한 상태.
        return new ThemeDTO.ThemeListResponse(DTOMapperService.of(themes));
    }


}
