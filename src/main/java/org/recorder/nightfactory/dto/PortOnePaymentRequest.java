package org.recorder.nightfactory.dto;
import org.springframework.beans.factory.annotation.Value;
import java.util.UUID;

public class PortOnePaymentRequest {
    private String storeId;
    private String channelKey;

    private UUID paymentId;
    private String orderName;
    private Long totalAmount;
    public String fullname;
    public String phoneNumber;

}



