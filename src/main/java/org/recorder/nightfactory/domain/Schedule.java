//package org.recorder.nightfactory.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//
//@Table(name = "schedule")
//@NoArgsConstructor
//@Getter
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//public class Schedule {
//
//    @ManyToOne(targetEntity = Thema.class)
//    @JoinColumn(name = "RoomId")
//    private roomd
//
//    @Column(name = "reservationDate")
//    private LocalDateTime reservationDate;
//
//    @Column(name = "startTime")
//    private String startTime;
//
//}
