package org.recorder.nightfactory.dto;


import lombok.*;
import org.recorder.nightfactory.domain.Theme;

import java.util.ArrayList;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ThemeDTO {
    private int roomId;
    private String name;
    private String description;
    private int difficulty;
    private String difficultyName;
    private String estimatedTime;
    private Long price;
    private String genre;


    private static ThemeDTO of(Theme theme) {
        return new ThemeDTO(
                theme.getRoomId()
                , theme.getName()
                , theme.getDescription()
                , theme.getDifficulty()
                , theme.getDifficultyName()
                , theme.getEstimatedTime()
                , theme.getPrice()
                , theme.getGenre().name()
        );
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThemeSet {
        ThemeDTO theme;
        ScheduleDTO.Schedules Schedules;

        public static ThemeSet of(org.recorder.nightfactory.domain.ThemeSet themeSet) {
            ThemeDTO theme = ThemeDTO.of(themeSet.getTheme());
            ScheduleDTO.Schedules schedules = ScheduleDTO.Schedules.of(themeSet.getSchedules());

            return new ThemeSet(theme, schedules);
        }
    }

    public static class Themes {
        public java.util.List<ThemeDTO> themes;

        public Themes(java.util.List<ThemeDTO> themes) {
            this.themes = themes;
        }

        public static Themes of(org.recorder.nightfactory.domain.Themes themes) {
            ArrayList<ThemeDTO> themeDTOs = new ArrayList<>();
            for (Theme theme : themes.get()) {
                themeDTOs.add(ThemeDTO.of(theme));
            }
            return new Themes(themeDTOs);
        }
    }

}
