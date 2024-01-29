package org.recorder.nightfactory.dto;

import lombok.*;

import java.util.List;


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


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThemeSet {
        ThemeDTO theme;
        ScheduleDTO.Schedules Schedules;

    }


    @Getter
    @AllArgsConstructor
    public static class ThemeListResponse {
        Themes themes;

        public List<ThemeDTO> getThemes() {
            return themes.getThemes();
        }

    }

    @Getter
    public static class Themes {
        public List<ThemeDTO> themes;

        public Themes(List<ThemeDTO> themes) {
            this.themes = themes;
        }
    }
}
