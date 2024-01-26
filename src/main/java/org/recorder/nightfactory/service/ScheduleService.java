package org.recorder.nightfactory.service;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.ThemeSets;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;

    public ReservationDTO.AllSchduleResponse findAllByDate(Date date) {
        ThemeSets themeSets = ThemeSets.findAll(themeRepository, scheduleRepository);
        themeSets.queryAllScheduleAble(reservationRepository, date);
        return ReservationDTO.AllSchduleResponse.of(themeSets);
    }

}
