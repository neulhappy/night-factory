package org.recorder.nightfactory.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.dto.ScheduleDTO;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {


    private final ReservationService reservationService;
    private ThemeService themeService;
    private PaymentService paymentService;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleService scheduleService;

    private final ReservationDetailService reservationDetailService;


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

    @GetMapping("/reservationCheck")
    public String reservationCancel(){
        return "reservationCheck";
    }

    @GetMapping("/reservationInfo")
    public String reservationInfo(){
        return "reservationInfo";
    }

    @GetMapping("/do")
    public String reservation(Model model){
        return "reservation";
    }

    @PostMapping("/make")
    public String makeReservation(@RequestParam String dateStr, @RequestParam int scheduleId, Model model, HttpSession session) {

        session.setAttribute("roomId", Schedule.findById(scheduleRepository, scheduleId).getTheme().getRoomId());
        session.setAttribute("themeName", Schedule.findById(scheduleRepository, scheduleId).getTheme().getName());
        session.setAttribute("reservationDate", dateStr);
        session.setAttribute("startTime", Schedule.findById(scheduleRepository, scheduleId).getStartTime());
        session.setAttribute("amount", Schedule.findById(scheduleRepository, scheduleId).getTheme().getPrice());

        return "reservationMake";
    }




    @PostMapping("/check")
    public String checkReservation(
            @RequestParam("resv-num") String scheduleId,
            @RequestParam("name") String owner,
            @RequestParam("phone-f") String phoneFirstPart,
            @RequestParam("phone-m") String phoneMiddlePart,
            @RequestParam("phone-l") String phoneLastPart) {

        ReservationDTO.GetRequest request = new ReservationDTO.GetRequest();
        ReservationDTO.GetResponse response = reservationDetailService.findByOwner(request);
        System.out.println("예약 정보: " + response.getReservation().toString());
        System.out.println("테마 이름: " + response.getThemeName());

        return "reservationCheckPost";
    }

    @PostMapping("/pay")
    public String makeReservation(
            @RequestParam("people") String numberOfPeople,
            @RequestParam("name") String owner,
            @RequestParam("phone-f") String phoneFirstPart,
            @RequestParam("phone-m") String phoneMiddlePart,
            @RequestParam("phone-l") String phoneLastPart,
            HttpSession session,
            Model model) {


        String phoneNumber = phoneFirstPart + "-" + phoneMiddlePart + "-" + phoneLastPart;
        model.addAttribute("numberOfPeople", numberOfPeople);
        model.addAttribute("owner", owner);
        model.addAttribute("phoneNumber", phoneNumber);

        return "reservationPay";
    }

//    @PostMapping("/payDone")
//    public String payReservation(){
//
////TODO:작업중이었음
//        reservationService.save(new ReservationDTO.RegisterRequest());
//
//        // 예약이 저장된 후에는 적절한 페이지로 리다이렉트합니다.
//        return "reservationSuccess";
//    }
}
