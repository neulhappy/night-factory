package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.recorder.nightfactory.repository.ThemeRepository;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Optional;


@Table(name = "Theme")
@NoArgsConstructor
@Entity
@Getter
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

    @Column(name = "estimated_time", nullable = false)
    private String estimatedTime;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Theme(String name, String description, Integer difficulty, String difficultyName, String estimatedTime, Long price, Genre genre) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.difficultyName = difficultyName;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.genre = genre;
    }
    public static Theme findById(ThemeRepository repository, int id) {
        Optional<Theme> themeRepo = repository.findById(id);
        if (themeRepo.isEmpty()) {
            throw new IllegalIdentifierException("No such theme");
        } else {
            return themeRepo.get();
        }
    }


    @PostLoad
    public void updateDifficultyName() {
        if (difficultyName.isBlank()) {
            this.difficultyName = Difficulty.getFromInt(difficulty).getName();
        }
    }

    public Schedules findSchedules(ScheduleRepository scheduleRepository) {
        return Schedules.ListSchedules(scheduleRepository, this);
    }

    public Long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public Genre getGenre() {
        return genre;
    }
}
