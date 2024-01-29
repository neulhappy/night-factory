package org.recorder.nightfactory.domain;

import net.nurigo.sdk.message.model.Message;


public interface SmsSend {

    //Get Api
    public void getApi(String apiKey, String secretKey, String domain);
    //Send void Message
    public int sendSMS(Smsable smsable);
    //Create Message(from:sendingNumber, to:ReceivingNumber, messageText)
    public Message createMessage(String to, String text);
}
