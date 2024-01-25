package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationPostRequest;
import org.recorder.nightfactory.dto.ReservationPostResponse;
import org.recorder.nightfactory.service.PaymentService;
import org.recorder.nightfactory.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReservationController {

    private ReservationService reservationService;
    private PaymentService paymentService;


    @GetMapping
    public String reservationView(Model model) {


        return "reserve";
    }
    @PostMapping
    public String postReservation(ReservationPostRequest request, Model model) {
        ReservationPostResponse response = reservationService.save(request);
        Reservation reservation = response.getReservation();
        Long price = response.getPrice();
        model.addAttribute("reservation", reservation);
        model.addAttribute("price", price);
        return "payment";
    }
}
