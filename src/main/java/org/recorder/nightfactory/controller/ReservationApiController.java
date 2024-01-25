package org.recorder.nightfactory.controller;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ThemeSchedulesListResponse;
import org.recorder.nightfactory.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;


@RestController
@RequiredArgsConstructor
public class ReservationApiController {
    private final ScheduleService scheduleService;
    @GetMapping("/api/schedule")
    public ResponseEntity<ThemeSchedulesListResponse> getThemeSchedulesList(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        if (date == null)
            date = Date.from(Instant.now().plusSeconds(86400));
        ThemeSchedulesListResponse themeSchedulesListResponse = scheduleService.findAllByDate(date);
        return ResponseEntity.ok().body(themeSchedulesListResponse);
    }


}
