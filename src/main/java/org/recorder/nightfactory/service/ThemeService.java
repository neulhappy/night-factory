package org.recorder.nightfactory.service;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.domain.ThemeSet;
import org.recorder.nightfactory.domain.Themes;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ScheduleRepository scheduleRepository;

    public ThemeDTO.ThemeListResponse themeList() {
        // ThemeRepository를 사용하여 테마 리스트 조회
        List<Theme> themeList = themeRepository.findAll();

        // 조회된 Theme 리스트를 ThemeDTO 리스트로 변환
        List<ThemeDTO> dtoList = themeList.stream()
                .map(this::convertToThemeDTO)
                .collect(Collectors.toList());

        // ThemeDTO 리스트를 ThemeListResponse 객체로 변환하여 반환
        return new ThemeDTO.ThemeListResponse(new ThemeDTO.Themes(dtoList));
    }

    // Theme 엔티티를 ThemeDTO로 변환하는 예시 메소드
    private ThemeDTO convertToThemeDTO(Theme theme) {
        // ThemeDTO 변환 로직 구현
        return new ThemeDTO(/* 필요한 인자를 전달하여 ThemeDTO 생성 */);
    }
}

