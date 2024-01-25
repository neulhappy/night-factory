package org.recorder.nightfactory.controller;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.dto.ReservationPostResponse;
import org.recorder.nightfactory.service.ReservationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
public class ReservationApiController {
    private final ReservationService reservationService;

//
//    @GetMapping("/api/schedule")
//    public ResponseEntity<>




}
