package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ThemeDTO;
import org.recorder.nightfactory.service.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/themes")
public class ThemesInformController {
    private final ThemeService themeService;

    @GetMapping("/content")
    public String ThemesContent(Model model) {
        String themeName = "ThemeName";
        model.addAttribute("themeName", themeName);
        return "theme";

    }


    @GetMapping("/list")
    public String ThemesString(Model model){
        return "theme";
    }
}
