package org.recorder.nightfactory.controller;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<ReservationDTO.AllSchduleResponse> getThemeSchedulesList(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        System.out.println("API 요청 수신: " + date);
        if (date == null)
            date = Date.from(Instant.now().plusSeconds(86400));
        ReservationDTO.AllSchduleResponse availableSchedules = scheduleService.findAllByDate(date);
        return ResponseEntity.ok().body(availableSchedules);
    }
}
