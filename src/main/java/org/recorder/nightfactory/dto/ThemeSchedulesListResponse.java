package org.recorder.nightfactory.dto;
import org.recorder.nightfactory.domain.ThemeSets;
public class ThemeSchedulesListResponse {
    ThemeSets themeSets;

    public ThemeSchedulesListResponse(ThemeSets themeSets) {
        this.themeSets = themeSets;
    }

    public ThemeSets getThemeSets() {
        return themeSets;
    }
}
