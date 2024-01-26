//Todo: DTO 생성 책임 서비스단으로 전부 이전,
// DTO는 생성자 혹은 Getter Setter 외의 책임 X 정적 팩토리 메소드 X


package org.recorder.nightfactory.dto;


import lombok.*;
import org.recorder.nightfactory.domain.Theme;

import java.util.ArrayList;
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

        public static Themes of(org.recorder.nightfactory.domain.Themes themes) {
            ArrayList<ThemeDTO> themeDTOs = new ArrayList<>();
            for (Theme theme : themes.get()) {
                themeDTOs.add(ThemeDTO.of(theme));
            }
            return new Themes(themeDTOs);
        }
    }

}
