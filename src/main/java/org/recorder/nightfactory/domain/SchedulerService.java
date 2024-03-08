package org.recorder.nightfactory.domain;

import lombok.RequiredArgsConstructor;
import org.recorder.nightfactory.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private static final long RATE_OF_RESERVATION_EXPIRING = 900000L;
    private static final Duration RANGE_OF_RESERVATION_EXPIRING = Duration.ofDays(1);
    private final ReservationRepository repository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(fixedRate = RATE_OF_RESERVATION_EXPIRING)
    public void reservationExpiring() {
        final LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minus(RANGE_OF_RESERVATION_EXPIRING);
        Reservations reservations = new Reservations(repository.findAllByStateAndReservationAtAfter(PaymentState.READY, startTime));

        reservations.performExpiration(now);

    }
}
