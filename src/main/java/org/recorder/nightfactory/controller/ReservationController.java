package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.service.PaymentService;
import org.recorder.nightfactory.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/detail")
    public String reservationDetail(@RequestParam Long scheduleId, Model model) {


        return "payment";
    }
//    @GetMapping("/payment")
//    public String payment() {
//
//
//    }
//    @PostMapping("/payment"){
//    }
}
