package org.recorder.nightfactory.domain;

import net.nurigo.sdk.message.model.Message;


public interface SmsSend {

    public void getApi(String apiKey, String secretKey, String domain);
    public void sendSMS();
    public Message createMessage(String to, String text);
}
