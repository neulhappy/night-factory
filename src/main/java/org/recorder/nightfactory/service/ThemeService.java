package org.recorder.nightfactory.service;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.domain.Themes;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public Themes findAll() {
        return Themes.ListThemes(themeRepository);
    }

    public Theme findById(Integer id) {

        Optional<Theme> theme = themeRepository.findById(id);
        if (theme.isEmpty()) {
            throw new IllegalArgumentException("No such Theme");
        } else {
            return theme.get();
        }
    }


}
