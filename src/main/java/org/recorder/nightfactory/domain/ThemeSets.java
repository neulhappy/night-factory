package org.recorder.nightfactory.domain;

import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;

import java.util.ArrayList;
import java.util.List;

public class ThemeSets {
    private final List<ThemeSet> themeSets;

    private ThemeSets(List<ThemeSet> themeSets) {
        this.themeSets = themeSets;
    }

    public static ThemeSets findAll(ThemeRepository themeRepository, ScheduleRepository scheduleRepository) {
        Themes themes = Themes.ListThemes(themeRepository);
        ArrayList<ThemeSet> themeSetList = new ArrayList<>();
        for (Theme theme : themes.getThemes()) {
            ThemeSet themeSet = ThemeSet.make(theme, scheduleRepository);
            themeSetList.add(themeSet);
        }
        return new ThemeSets(themeSetList);
    }

    public List<ThemeSet> getThemeSets() {
        return themeSets;
    }
}
