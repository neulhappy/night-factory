package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.PaymentState;
import org.recorder.nightfactory.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findByOwnerOrderByReservationDateDesc(String owner);

    List<Reservation> findAllByReservationDateAndStateIn(Date reservationDate, List<PaymentState> states);
}
