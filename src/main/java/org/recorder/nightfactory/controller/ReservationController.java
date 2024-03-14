package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.service.PaymentService;
import org.recorder.nightfactory.service.ReservationService;
import org.recorder.nightfactory.service.ThemeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;
    private ThemeService themeService;
    private PaymentService paymentService;


    @GetMapping
    public String reservationView(
            @RequestParam(name = "room") int themeId
            , @RequestParam(name = "time") int scheduleId
            , @RequestParam(name = "day") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
            , Model model) {

        return "reservation";
    }

    @PostMapping
    public String postReservation(@ModelAttribute ReservationDTO.RegisterRequest request, Model model) {
        ReservationDTO.RegisterResponse response = reservationService.save(request);
        Long price = response.getPrice();
        model.addAttribute("response", response);
        return "payment";
    }
}
