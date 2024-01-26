package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.repository.ScheduleRepository;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;


@Table(name = "schedule")
@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Theme theme;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Transient
    private Boolean able;

    void setAble(boolean able) {
        this.able = able;
    }

    public Boolean isAble() {
        return this.able;
    }

    public boolean isIncludedByIds(HashSet<Integer> ids) {
        return ids.contains(this.Id);
    }

    public static Schedule findById(ScheduleRepository repository, Integer id) {
        Optional<Schedule> schedule = repository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("No such schedule");
        } else
            return schedule.get();
    }
}
