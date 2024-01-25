package org.recorder.nightfactory.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "reservations")
@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Temporal(TemporalType.DATE)
    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "reservation_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime reservationAt;

    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @Column(name = "imp_uid")
    String impUid;

    @Column(name = "amount")
    Long amount;

    @Column(name = "payment_state")
    @Enumerated(EnumType.STRING)
    PaymentState state;

    @Column(name = "paid_at")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime paidAt;


    public Reservation(Schedule schedule, String owner, Date reservationDate, String phoneNumber, int numberOfPeople) {
        this.schedule = schedule;
        this.owner = owner;
        this.reservationDate = reservationDate;
        this.phoneNumber = phoneNumber;
        this.numberOfPeople = numberOfPeople;
    }

    public boolean isReserved() {
        //예약이 이미 되어있는지 확인하는 로직

        return false;
    }


}
