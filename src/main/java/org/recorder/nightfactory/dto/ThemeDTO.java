//Todo: DTO 생성 책임 서비스단으로 전부 이전,
// DTO는 생성자 혹은 Getter Setter 외의 책임 X 정적 팩토리 메소드 X


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
