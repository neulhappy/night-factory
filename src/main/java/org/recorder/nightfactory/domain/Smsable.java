package org.recorder.nightfactory.domain;

import net.nurigo.sdk.message.model.Message;

public interface Smsable {
    public String getPhoneNumber();
    public String getMessageText();

}
