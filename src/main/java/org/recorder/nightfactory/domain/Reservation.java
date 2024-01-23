package org.recorder.nightfactory.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.service.ScheduleService;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Calendar;

@Table(name = "reservations")
@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scheduleId", referencedColumnName = "id")
    private Schedule schedule;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "reservationDate")
    private Calendar reservationDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "numberOfPeople")
    private Integer numberOfPeople;

    @Column(name = "reservationAt")
    @LastModifiedDate
    private Calendar reservationAt;

    public Reservation(Schedule schedule, String owner, Calendar reservationDate, String phoneNumber, int numberOfPeople) {
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
