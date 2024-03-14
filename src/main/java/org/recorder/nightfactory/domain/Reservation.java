package org.recorder.nightfactory.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Table(name = "reservations")
@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation implements Smsable {

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

    @Column(name = "payment_id", columnDefinition = "BINARY(16)")
    UUID paymentId;

    @Column(name = "imp_uid")
    String impUid;

    @Column(name = "amount")
    Long amount;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    PaymentState state;

    @Column(name = "paid_at")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime paidAt;

    @Column(name = "receipt_url")
    String receipt;


    public static Reservation findById(UUID id, ReservationRepository repository) {
        Optional<Reservation> reservationRepo = repository.findById(id);
        if (reservationRepo.isEmpty()) {
            throw new IllegalIdentifierException("No such reservation");
        } else {
            return reservationRepo.get();
        }
    }


    private Reservation(Schedule schedule, String owner, Date reservationDate, String phoneNumber, int numberOfPeople) {
        this.schedule = schedule;
        this.owner = owner;
        this.reservationDate = reservationDate;
        this.phoneNumber = phoneNumber;
        this.numberOfPeople = numberOfPeople;
    }


    boolean isExpired(LocalDateTime now) {
        final Duration EXPIRE_TIME = Duration.ofMinutes(30);
        boolean isElapsed = Duration.between(this.reservationAt, now).compareTo(EXPIRE_TIME) > 0;
        return state == PaymentState.READY && isElapsed;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void expire() {
        this.updateStatus(PaymentState.FAILED);
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public void updateStatus(PaymentState paymentState) {
        this.state = paymentState;
    }

    @Override
    public String getMessageText() {
        SimpleDateFormat reservationDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reservationDateFormatted = reservationDateFormat.format(this.getReservationDate().getTime());
        return String.format("[예약 번호 안내]\n%s님\n%s에\n'%s'\n%d명 예약 되었습니다.\n예약 번호 : %s\n예약 시간 : %s"
                , this.getOwner()
                , reservationDateFormatted
                , this.getSchedule().getTheme().getName()
                , this.getNumberOfPeople()
                , this.getId()
                , this.getSchedule().getStartTime());
    }
}

class Reservations {
    private final List<Reservation> reservations;

    public Reservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void performExpiration(LocalDateTime now) {
        for (Reservation reservation : reservations)
            if (reservation.isExpired(now)) {
                reservation.expire();
            }
    }

}


