package org.recorder.nightfactory.domain;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class NurigoService implements SmsSend{
    private Reservation reservation;
    private DefaultMessageService messageService;
    private final String SENDER_NUMBER = "01044444444";

    public NurigoService(@Value("${api.coolSms.apiKey}") String apiKey
            , @Value("${api.coolSms.apiSecretKey}") String secretKey
            , @Value("${api.coolSms.domain}") String domain) {
        getApi(apiKey, secretKey, domain);
    }

    public DefaultMessageService getCoolSmsApiToken() {
        return messageService;
    }

//    SingleMessageSentResponse sendReservationSMS(Smsable smsable){
//        Message message = createMessage(smsable.getPhoneNumber(), smsable.getMessageText());
//        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
//        return response;
//    }

    public void getApi(String apiKey, String secretKey, String domain) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, secretKey, domain);
    }

    @Override
    public void sendSMS() {
        Message message = createMessage(reservation.getPhoneNumber(), reservation.getMessageText());
        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException e) {
            throw new RuntimeException(e);
        } catch (NurigoEmptyResponseException e) {
            throw new RuntimeException(e);
        } catch (NurigoUnknownException e) {
            throw new RuntimeException(e);
        }
    }

    public Message createMessage(String to, String text) {
        Message message = new Message();
        message.setFrom(SENDER_NUMBER);
        message.setTo(to);
        message.setText(text);
        return message;
    }
}
