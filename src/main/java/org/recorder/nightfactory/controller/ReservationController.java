package org.recorder.nightfactory.controller;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.service.ReservationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
//
//    @GetMapping("/api/schedule")
//    public ResponseEntity<>

    @PostMapping("/api/reservation")
    public String postReservation(ReservationPostRequest request){
            reservationService.save(request);
        return "payment";
    }


}
