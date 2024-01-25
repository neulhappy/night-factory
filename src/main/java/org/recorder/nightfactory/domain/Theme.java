package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Table(name = "Theme")
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Theme {
    @Id()
    @Column(name = "room_id")
    private Integer roomId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer difficulty;
    @Column(name = "alt_difficulty")
    private String difficultyName;

    @Column(nullable = false)
    private String estimatedTime;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Theme(String name, String description, String difficultyName, String estimatedTime, String genre) {
    }

    @PostLoad
    public void getDifficulty() {
        if (difficultyName.isBlank()) {
            this.difficultyName = Difficulty.getFromInt(difficulty).getName();
        }
    }

    public Schedules findSchedules(ScheduleRepository scheduleRepository) {
        return Schedules.ListSchedules(scheduleRepository, this);
    }

    public Long getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }
}
