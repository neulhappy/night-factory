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
        for (Theme theme : themes.get()) {
            ThemeSet themeSet = ThemeSet.make(theme, scheduleRepository);
            themeSetList.add(themeSet);
        }
        return new ThemeSets(themeSetList);
    }

    public List<ThemeSet> getThemeSets() {
        return themeSets;
    }


    public void queryAllScheduleAble(ReservationRepository repository, Date date) {
        List<PaymentState> includeState = Arrays.asList(PaymentState.READY, PaymentState.PAID, PaymentState.ADMIN);
        List<Reservation> reservationsOfDate = repository.findAllByReservationDateAndStateIn(date, includeState);
        HashSet<Integer> reservedSchedulesIds = new HashSet<>();
        for (Reservation reservation : reservationsOfDate) {
            reservedSchedulesIds.add(reservation.getSchedule().getId());
        }
        if (reservedSchedulesIds.isEmpty()) {
            for (ThemeSet set : this.getThemeSets())
                for (Schedule schedule : set.getSchedules().get()) {
                    schedule.setAble(true);
                }
        } else {
            for (ThemeSet set : this.getThemeSets())
                for (Schedule schedule : set.getSchedules().get()) {
                    schedule.setAble(!schedule.isIncludedByIds(reservedSchedulesIds));
                }
        }
    }
}
