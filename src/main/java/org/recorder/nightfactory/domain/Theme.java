package org.recorder.nightfactory.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;


@Table(name = "Thema")
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

    @Enumerated(EnumType.STRING)
    private Genre genre;


    Schedules findSchedules()

}
//@Converter(autoApply = true)
//class DurationConverter implements AttributeConverter<Duration, Long>{
//
//    @Override
//    public Long convertToDatabaseColumn(Duration attribute) {
//        return attribute.toMinutes();
//    }
//
//    @Override
//    public Duration convertToEntityAttribute(Long dbData) {
//        return Duration.ofMinutes(dbData);
//    }
//}
