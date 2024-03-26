package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.domain.Themes;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    List<Theme> findAllByOrderByRoomIdAsc();

}
