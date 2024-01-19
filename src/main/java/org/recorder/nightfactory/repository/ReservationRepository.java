package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByOwnerOrderByReservationDateDesc(String owner);
}
