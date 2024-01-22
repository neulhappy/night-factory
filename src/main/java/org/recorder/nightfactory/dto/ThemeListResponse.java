package org.recorder.nightfactory.dto;

import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.domain.Themes;

import java.util.List;

public class ThemeListResponse {
    public Themes themes;

    public ThemeListResponse(Themes themes) {
        this.themes = themes;
    }

    public List<Theme> getThemes() {
        return themes.getThemes();
    }
}
