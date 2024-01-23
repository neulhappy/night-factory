package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;


@Table(name = "Theme")
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Theme {
    @Id()
    @Column(name = "room_id")
    private Integer roomId;

    private String name;

    private String description;

    private Integer difficulty;

    private Duration estimatedTime;

    private Long price;

    @Enumerated(EnumType.STRING)
    private Genre genre;


    public Schedules findSchedules(ScheduleRepository scheduleRepository) {
        return Schedules.ListSchedules(scheduleRepository, this);
    }

    public Long getPrice() {
        return price;
    }

    public String getName() { return name;}
}
