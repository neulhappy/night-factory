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

    public Optional<Theme> findById(Integer id) {
        return themeRepository.findById(id);
    }


}
