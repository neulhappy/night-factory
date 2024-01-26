package org.recorder.nightfactory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.recorder.nightfactory.domain.ThemeSet;
import org.recorder.nightfactory.domain.ThemeSets;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ReservationDTO {

    public static class GetRequest {

    }

    public static class GetResponse {

    }

    @NoArgsConstructor
    @Getter
    public static class PostRequest {
        private int scheduleId;
        private Date reservationDate;
        private String owner;
        private String phoneNumber;
        private int numberOfPeople;
        private LocalDateTime reservationAt;
    }


    @Getter
    public static class PostResponse {
        PaymentDTO paymentDTO;
        Long price;
    }

    @NoArgsConstructor
    @Getter
    public static class DeleteRequest {

    }


    public static class DeleteResponse {

    }


    public static class AllSchduleResponse {
        List<ThemeDTO.ThemeSet> allSchedules;


        private AllSchduleResponse(List<ThemeDTO.ThemeSet> allSchedules) {
            this.allSchedules = allSchedules;
        }

        public static AllSchduleResponse of(ThemeSets themeSets) {
            List<ThemeDTO.ThemeSet> allSchedules = new ArrayList<>();
            for (ThemeSet themeSet : themeSets.getThemeSets()) {
                allSchedules.add(ThemeDTO.ThemeSet.of(themeSet));
            }
            return new AllSchduleResponse(allSchedules);
        }
    }
}
