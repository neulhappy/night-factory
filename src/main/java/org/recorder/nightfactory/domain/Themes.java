package org.recorder.nightfactory.domain;

import org.recorder.nightfactory.repository.ThemeRepository;

import java.util.List;

public class Themes {
    private final List<Theme> themes;

    private Themes(List<Theme> themes) {
        this.themes = themes;
    }

    public static Themes listTheme(ThemeRepository themeRepository) {
        return new Themes(themeRepository.findAllByOrderByRoomIdAsc());
    }

    public List<Theme> get() {
        return themes;
    }
}

