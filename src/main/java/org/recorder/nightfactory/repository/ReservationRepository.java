package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.PaymentState;
import org.recorder.nightfactory.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByOwnerOrderByReservationDateDesc(String owner);

    List<Reservation> findAllByReservationDateAndStateIn(Date reservationDate, List<PaymentState> states);

    Optional<Reservation> getById(UUID id);
}
