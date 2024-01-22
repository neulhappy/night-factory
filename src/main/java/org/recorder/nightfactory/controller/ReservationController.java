package org.recorder.nightfactory.controller;


import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.dto.ReservationPostResponse;
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
    public String postReservation(ReservationPostRequest request, Model model){
            ReservationPostResponse response =  reservationService.save(request);
            Reservation reservation = response.getReservation();
            Long price = response.getPrice();
            model.addAttribute("reservation", reservation);
            model.addAttribute("price", price);
        return "payment";
    }


}
