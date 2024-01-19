package org.recorder.nightfactory.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

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

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "reservationDate")
    private LocalDateTime reservationDate;

    @Column(name = "roomName")
    private String roomName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "numberOfPeople")
    private int numberOfPeople;

    @Column(name = "price")
    private int price;

    @Column(name = "reservationTime")
    @LastModifiedDate
    private LocalDateTime reservationTime;

    @Builder
    public Reservation(String owner, LocalDateTime reservationDate, String roomName, String phoneNumber, int numberOfPeople, int price) {
        this.owner = owner;
        this.reservationDate = reservationDate;
        this.roomName = roomName;
        this.phoneNumber = phoneNumber;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
    }

    public boolean isReserved(){
        //예약이 이미 되어있는지 확인하는 로직

        return false;
    }

}
