package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.ThemeSet;
import org.recorder.nightfactory.domain.ThemeSets;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;
    private final DTOMapperService dtoMapperService;

    public ReservationDTO.AllSchduleResponse findAllByDate(Date date) {
        ThemeSets themeSets = ThemeSets.findAll(themeRepository, scheduleRepository);
        themeSets.queryAllScheduleAble(reservationRepository, date);
        return allScheduleResponseBy(themeSets);
    }

    public ReservationDTO.AllSchduleResponse allScheduleResponseBy(ThemeSets themeSets) {
        List<ThemeDTO.ThemeSet> allSchedules = new ArrayList<>();
        for (ThemeSet themeSet : themeSets.getThemeSets()) {
            allSchedules.add(dtoMapperService.of(themeSet));
        }
        return new ReservationDTO.AllSchduleResponse(allSchedules);
    }
}


