package org.recorder.nightfactory.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.domain.Schedule;
import org.recorder.nightfactory.dto.ReservationDTO;
import org.recorder.nightfactory.dto.ScheduleDTO;
import org.recorder.nightfactory.repository.PaymentRepository;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
    private final PaymentRepository paymentRepository;


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
        ReservationDTO.RegisterResponse response = reservationService.paymentSave(request);
        Long price = response.getPrice();
        model.addAttribute("response", response);
        return "payment";
    }

    @GetMapping("/check")
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
    public String makeReservation(@RequestParam String dateStr, @RequestParam int scheduleId, Model model, HttpSession session) throws ParseException {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date reservationDate = inputFormat.parse(dateStr);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = outputFormat.format(reservationDate);

        session.setAttribute("scheduleId", scheduleId);
        session.setAttribute("roomId", Schedule.findById(scheduleRepository, scheduleId).getTheme().getRoomId());
        session.setAttribute("themeName", Schedule.findById(scheduleRepository, scheduleId).getTheme().getName());
        session.setAttribute("reservationDate", formattedDate);
        session.setAttribute("reservationDateParsing", reservationDate);
        session.setAttribute("startTime", Schedule.findById(scheduleRepository, scheduleId).getStartTime());
        session.setAttribute("amount", Schedule.findById(scheduleRepository, scheduleId).getTheme().getPrice());

        ReservationDTO.RegisterRequest request = new ReservationDTO.RegisterRequest();
        long amount = (Long) session.getAttribute("amount");

        request.setScheduleId(scheduleId);
        request.setReservationDate(reservationDate);
        request.setAmount(amount);

        ReservationDTO.RegisterResponse response = reservationService.make(request);


        return "reservationMake";
    }

    @PostMapping("/pay")
    public String payReservation(
            @RequestParam("people") String numberOfPeople,
            @RequestParam("name") String owner,
            @RequestParam("phone-f") String phoneFirstPart,
            @RequestParam("phone-m") String phoneMiddlePart,
            @RequestParam("phone-l") String phoneLastPart,
            HttpSession session,
            Model model) {


        String phoneNumber = phoneFirstPart + "-" + phoneMiddlePart + "-" + phoneLastPart;
        session.setAttribute("numberOfPeople", numberOfPeople);
        session.setAttribute("owner", owner);
        session.setAttribute("phoneNumber", phoneNumber);

        ReservationDTO.RegisterRequest request = new ReservationDTO.RegisterRequest();
        request.setOwner(owner);
        request.setPhoneNumber(phoneNumber);
        request.setNumberOfPeople(Integer.parseInt(numberOfPeople));
        LocalDateTime reservationAt = LocalDateTime.now();
        request.setReservationAt(reservationAt);

        ReservationDTO.RegisterResponse response = reservationService.paying(request);
        Reservation reservation = reservationDetailService.findByReservationAt(reservationAt);

        String reservationNumber = String.valueOf(reservation.getId()).substring(9, 13);
        session.setAttribute("reservationNumber", reservationNumber);

        return "reservationPay";
    }

    @PostMapping("/paying")
    public String payingReservation(HttpSession session){


//        reservationService.save(new ReservationDTO.RegisterRequest());

        // 예약이 저장된 후에는 적절한 페이지로 리다이렉트합니다.
        return "reservationPay";
    }

    @GetMapping("/success")
    public String SuccessReservation(HttpSession session){
        ReservationDTO.RegisterRequest request = new ReservationDTO.RegisterRequest();
        long amount = (Long) session.getAttribute("amount");
        request.setAmount(amount);

        ReservationDTO.RegisterResponse response = reservationService.paymentSave(request);



        return "reservationSuccess";
    }

    @PostMapping("/check")
    public String checkReservation(
            @RequestParam("resv-num") String uuid,
            @RequestParam("name") String owner,
            @RequestParam("phone-f") String phoneFirstPart,
            @RequestParam("phone-m") String phoneMiddlePart,
            @RequestParam("phone-l") String phoneLastPart,
            HttpSession session,
            Model model) {

//        ReservationDTO.GetRequest request = new ReservationDTO.GetRequest();
//        request.setOwner(owner);

        String phoneNumber = phoneFirstPart + "-" + phoneMiddlePart + "-" + phoneLastPart;
        Reservation reservation = reservationDetailService.findByReservationId(uuid, owner, phoneNumber);

        if(reservation !=null){
        model.addAttribute("reservationNumber", uuid);
        model.addAttribute("owner", reservation.getOwner());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("reservationDate", dateFormat.format(reservation.getReservationDate()));
        model.addAttribute("phoneNumber", reservation.getPhoneNumber());
        model.addAttribute("amount", reservation.getAmount());
        model.addAttribute("numberOfPeople", reservation.getNumberOfPeople());
        model.addAttribute("roomId", reservation.getSchedule().getTheme().getRoomId());
        model.addAttribute("themeName", reservation.getSchedule().getTheme().getName());
        model.addAttribute("startTime", reservation.getSchedule().getStartTime());
        session.setAttribute("reservation", reservation);

            return "reservationCheckPost";
        }else {
            return "reservationAlert";
        }
    }


    @PostMapping("delete")
    public String deleteReservation(HttpSession session){
        ReservationDTO.DeleteRequest request = new ReservationDTO.DeleteRequest();
        Reservation reservation = (Reservation) session.getAttribute("reservation");
        request.setReservation(reservation);
        reservationService.delete(request);


        return "reservationDeleteAlert";
    }
}
