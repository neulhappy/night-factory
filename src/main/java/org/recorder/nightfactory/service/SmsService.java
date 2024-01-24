package org.recorder.nightfactory.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.recorder.nightfactory.domain.Reservation;
import org.recorder.nightfactory.dto.ReservationPostResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

@Service
public class SmsService {

    private final DefaultMessageService messageService;
    private final ReservationService reservationService;

    private final String SENDER_NUMBER = "01044444444";
    private final String RESERVATION_TEXT = "[예약 번호 안내]\n%s님\n%s에\n'%s'\n%d명 예약 되었습니다.\n예약 번호 : %d\n예약 시간 : %s";

    public SmsService (@Value("${api.coolSms.apiKey}") String apiKey
            , @Value("${api.coolSms.apiSecretKey}") String secretKey
            , @Value("${api.coolSms.domain}") String domain, ReservationService reservationService) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, secretKey, domain);
        this.reservationService = reservationService;
    }

    public DefaultMessageService getCoolSmsApiToken() {
        return messageService;
    }
    
    SingleMessageSentResponse sendReservationSMS(ReservationPostResponse response){
        Reservation reservation = getReservationFromResponse(response);
        Message message = createReservationMessage(reservation);
        SingleMessageSentResponse resp = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(resp);
        return resp;
    }

    private Reservation getReservationFromResponse(ReservationPostResponse response){
        Long reservationId = response.getReservation().getId();
        return reservationService.findById(reservationId);
    }

    private Message createReservationMessage(Reservation reservation){
        String phoneNumber = reservation.getPhoneNumber();
        String messageText = getReservationText(reservation);
        return createMessage(SENDER_NUMBER, phoneNumber, messageText);
    }

    private Message createMessage(String from, String to, String text){
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setText(text);
        return message;
    }

    private String getReservationText(Reservation reservation){
        SimpleDateFormat reservationDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat reservationAtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reservationDateFormatted = reservationDateFormat.format(reservation.getReservationDate().getTime());
        String reservationAtFormatted = reservationAtFormat.format(reservation.getReservationAt().getTime());
        return String.format(RESERVATION_TEXT,
                reservation.getOwner(),
                reservationDateFormatted,
                reservation.getSchedule().getTheme().getName(),
                reservation.getNumberOfPeople(),
                reservation.getId(),
                reservationAtFormatted);
    }
}
