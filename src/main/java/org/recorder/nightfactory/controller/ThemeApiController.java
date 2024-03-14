package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.recorder.nightfactory.repository.ThemeServiceRepository;
import org.recorder.nightfactory.service.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/themes")
public class ThemeApiController {
    private final ThemeServiceRepository themeService;

    @GetMapping
    public ResponseEntity<List<ThemeDTO>> getAllThemes() {
        List<ThemeDTO> themes = themeService.getAllThemes();
        return ResponseEntity.ok().body(themes);
    }
}