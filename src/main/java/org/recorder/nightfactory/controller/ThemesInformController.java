package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Theme;
import org.recorder.nightfactory.domain.ThemeSets;
import org.recorder.nightfactory.domain.Themes;
import org.recorder.nightfactory.dto.ThemeListResponse;
import org.recorder.nightfactory.dto.ThemeName;
import org.recorder.nightfactory.dto.ThemeSchedulesListResponse;
import org.recorder.nightfactory.service.ThemeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/themes")
public class ThemesInformController {
    private ThemeService themeService;

    @GetMapping
    public String ThemesInform(Model model) {
        ThemeListResponse response = themeService.themeList();
        List<Theme> themes = response.getThemes();
        model.addAttribute(themes);

        return "theme";
    }

    @GetMapping("/content")
    public String ThemesContent(Model model){
        String themeName = "ThemeName";
        model.addAttribute("themeName", themeName);
        return "theme";


    }


}
