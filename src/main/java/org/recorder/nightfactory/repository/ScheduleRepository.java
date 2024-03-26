package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByThemeOrderByStartTimeAsc(Theme theme);
    List<Schedule> findAllByDate(LocalDate date);

    Optional<Schedule> findById(int id);
}
