package org.recorder.nightfactory.repository;

import org.recorder.nightfactory.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Reservation, UUID> {

    Optional<Reservation> findByPaymentId(UUID uuid);
}

