package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    UUID paymentId;
    String OrderName;
    Long totalAmount;
    @Enumerated(EnumType.STRING)
    PaymentState state;
    LocalDateTime paidAt;


}

