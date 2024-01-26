package org.recorder.nightfactory.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.recorder.nightfactory.domain.Smsable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final DefaultMessageService messageService;
    private final String SENDER_NUMBER = "01044444444";

    public SmsService(@Value("${api.coolSms.apiKey}") String apiKey
            , @Value("${api.coolSms.apiSecretKey}") String secretKey
            , @Value("${api.coolSms.domain}") String domain) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, secretKey, domain);
    }

    public DefaultMessageService getCoolSmsApiToken() {
        return messageService;
    }

//    SingleMessageSentResponse sendReservationSMS(Smsable smsable){
//        Message message = createMessage(smsable.getPhoneNumber(), smsable.getMessageText());
//        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
//        return response;
//    }

    private int sendSMS(Smsable smsable) {
        Message message = createMessage(smsable.getPhoneNumber(), smsable.getMessageText());
        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException e) {
            return -1;
        } catch (Exception exception) {
            return -2;
        }
        return 1;
    }

    private Message createMessage(String to, String text) {
        Message message = new Message();
        message.setFrom(SENDER_NUMBER);
        message.setTo(to);
        message.setText(text);
        return message;
    }
}
