package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.dto.ThemeDTO;

import java.util.List;

public interface ThemeServiceRepository {
    List<ThemeDTO> getAllThemes();
}
