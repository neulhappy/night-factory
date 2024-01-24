package org.recorder.nightfactory.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
    @JoinColumn(name = "scheduleId", referencedColumnName = "id")
    private Schedule schedule;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Temporal(TemporalType.DATE)
    @Column(name = "reservationDate")
    private Date reservationDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "numberOfPeople")
    private Integer numberOfPeople;

    @Temporal(TemporalType.DATE)
    @Column(name = "reservationAt")
    @LastModifiedDate
    private Date reservationAt;

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
