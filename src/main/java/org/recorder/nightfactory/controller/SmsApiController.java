package org.recorder.nightfactory.controller;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationPostResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;
import java.util.Calendar;

@RestController
@RequiredArgsConstructor
public class SmsApiController {

    private final DefaultMessageService messageService;

    public SmsApiController(){
        //계정 내 등록된 api, secret key 입력
        this.messageService = NurigoApp.INSTANCE.initialize("api key", "api key password", "https://api.coolsms.co.kr");
    }



    @PostMapping("/api/sendReservationSMS")
    @ResponseBody
    SingleMessageSentResponse sendReservationSMS(@RequestBody ReservationPostResponse response){
        Message message = new Message();
        Reservation reservation = response.getReservation();
        //TODO: 예약 번호를 가져 오는 로직
        String reservationNumber = null;

        //예약 정보를 가져 오는 로직
        String phoneNumber = reservation.getPhoneNumber();
        String owner = reservation.getOwner();
        Calendar reservationDate = reservation.getReservationDate();
        String roomName = reservation.getSchedule().getTheme().getName();
        LocalTime startTime = reservation.getSchedule().getStartTime();
        Integer numberOfPeople = reservation.getNumberOfPeople();
        Calendar reservationAt = reservation.getReservationAt();

        message.setFrom("01044444444");
        message.setTo(phoneNumber);
        message.setText("[예약 번호 안내] " + owner + "님" );
        message.setText(reservationDate + "--" + startTime + "에");
        message.setText("'" + roomName + "'");
        message.setText(numberOfPeople + "명");
        message.setText("예약 되었습니다.");

        message.setText("예약 일시 : " + reservationAt );

        SingleMessageSentResponse resp = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(resp);

        return resp;
    }

    @PostMapping("/api/sendRefundSMS")
    @ResponseBody
    SingleMessageSentResponse sendRefundSMS(@RequestBody ReservationPostResponse response){
        Message message = new Message();
        Reservation reservation = response.getReservation();
        //TODO: 예약 번호를 가져 오는 로직
        String reservationNumber = null;

        //환불 정보를 가져 오는 로직
        String phoneNumber = reservation.getPhoneNumber();
        String owner = reservation.getOwner();
        Calendar reservationDate = reservation.getReservationDate();
        String roomName = reservation.getSchedule().getTheme().getName();
        LocalTime startTime = reservation.getSchedule().getStartTime();
        Integer numberOfPeople = reservation.getNumberOfPeople();

        message.setFrom("01044444444");
        message.setTo(phoneNumber);
        message.setText("[환불 정보 안내] " + owner + "님" );
        message.setText(reservationDate + "--" + startTime + "에");
        message.setText("'" + roomName + "'");
        message.setText(numberOfPeople + "명");
        message.setText("환불 되었습니다.");


        SingleMessageSentResponse resp = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(resp);

        return resp;
    }
}
