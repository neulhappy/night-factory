package org.recorder.nightfactory.dto;

import lombok.*;
import org.recorder.nightfactory.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private Long number;
    private String author;
    private String title;
    private String content;
    private String phone;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        return Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .password(password)
                .phone(phone)
                .build();
    }

    @Builder
    public BoardDTO(Long id, String author, String title, String content, String phone, String password, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.phone = phone;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
