package org.recorder.nightfactory.domain;

import org.recorder.nightfactory.repository.ReservationRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;

import java.util.*;

public class ThemeSets {
    private final List<ThemeSet> themeSets;

    private ThemeSets(List<ThemeSet> themeSets) {
        this.themeSets = themeSets;
    }

    public static ThemeSets findAll(ThemeRepository themeRepository, ScheduleRepository scheduleRepository) {
        Themes themes = Themes.listTheme(themeRepository);
        ArrayList<ThemeSet> themeSetList = new ArrayList<>();
        for (Theme theme : themes.getThemes()) {
            ThemeSet themeSet = ThemeSet.make(theme, scheduleRepository);
            themeSetList.add(themeSet);
        }
        return new ThemeSets(themeSetList);
    }

    public List<ThemeSet> getThemeSets() {
        return themeSets;
    }


    //대량 데이터 처리 시 캐싱 필요.
    public void queryAllScheduleAble(Date date, ReservationRepository repository) {
        List<Reservation> reservationsOfDate = repository.findAllByReservationDate(date);
        HashSet<Integer> reservedSchedulesIds = new HashSet<>();
        for (Reservation reservation : reservationsOfDate) {
            reservedSchedulesIds.add(reservation.getSchedule().getId());
        }
        if (reservedSchedulesIds.isEmpty()) {
            for (ThemeSet set : this.getThemeSets())
                for (Schedule schedule : set.getSchedules()) {
                    schedule.setAble(true);
                }
        } else {
            for (ThemeSet set : this.getThemeSets())
                for (Schedule schedule : set.getSchedules()) {
                    schedule.setAble(!schedule.isIncludedByIds(reservedSchedulesIds));
                }
        }
    }
}
