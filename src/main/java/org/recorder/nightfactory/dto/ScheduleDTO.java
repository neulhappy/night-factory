package org.recorder.nightfactory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.domain.Theme;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Integer Id;
    private Theme theme;
    private LocalTime startTime;
    private Boolean able;

    public static ScheduleDTO of(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(), schedule.getTheme(), schedule.getStartTime(), schedule.getAble());
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Schedules {
        List<ScheduleDTO> schedules;

        public static Schedules of(org.recorder.nightfactory.domain.Schedules schedules) {
            List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
            for (Schedule schedule : schedules.get())
                scheduleDTOs.add(ScheduleDTO.of(schedule));

            return new Schedules(scheduleDTOs);
        }


    }

}
