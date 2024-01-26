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


    //대량 데이터 처리 시 캐싱 필요.
    // todo: 현 사양으로는 결제 준비 상태에서도 목록에 보이기 때문에 동시 예약 문제가 일어날 수 있음.
    //       다만 Ready를 포함할 경우 실제 결제를 실시하지 않고 Ready상태에서 이탈한 사용자에 의해서도 스케쥴이 점유됨.
    public void queryAllScheduleAble(ReservationRepository repository, Date date) {
        List<PaymentState> includeState = Arrays.asList(PaymentState.PAID, PaymentState.ADMIN);
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
