package org.recorder.nightfactory.domain;

import org.recorder.nightfactory.repository.ThemeRepository;

import java.util.List;

public class Themes {
    private final List<Theme> themes;

    private Themes(ThemeRepository themeRepository) {
        this.themes = (themeRepository.findAllByOrderByRoomIdAsc());
    }

    public static Themes generateThemes(ThemeRepository themeRepository) {
        return new Themes(themeRepository);
    }
}

