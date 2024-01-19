package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "schedule")
@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @OneToOne(targetEntity = Theme.class)
    @JoinColumn(name = "room_id")
    private int roomId;

    @Column(name = "reservationDate")
    private LocalDateTime reservationDate;

    @Column(name = "startTime")
    private String startTime;

}
