package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.Genre;
import org.recorder.nightfactory.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    List<Theme> findAllByGenreOrderByRoomIdAsc(Genre genre);

}
