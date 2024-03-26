package org.recorder.nightfactory.repository;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ThemeServiceRepositoryImpl implements ThemeServiceRepository {
    private final ThemeRepository themeRepository; // JPA Repository

    @Override
    public List<ThemeDTO> getAllThemes() {
        // Theme 엔티티 목록을 검색
        List<Theme> themes = themeRepository.findAll();
        // Theme 엔티티 목록을 ThemeDTO 객체 리스트로 변환
        return themes.stream().map(this::convertToThemeDTO).collect(Collectors.toList());
    }
    // Theme 엔티티를 ThemeDTO 객체로 변환하는 메서드
    private ThemeDTO convertToThemeDTO(Theme theme) {
        return new ThemeDTO(
                theme.getRoomId(), // ID 또는 roomId 필요에 따라 수정
                theme.getName(),
                theme.getDescription(),
                theme.getDifficulty(),
                theme.getDifficultyName(),
                theme.getEstimatedTime(),
                theme.getPrice(),
                theme.getGenre().name() // genre 필드 처리 방식에 따라 수정
        );
    }
}
