package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByThemeOrderByStartTimeAsc(Theme theme);
}
