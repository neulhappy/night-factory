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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDTO(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public boolean isContentBlank() {
        return content == null || content.trim().isEmpty();
    }
    public boolean isAuthorBlank() {
        return author == null || author.trim().isEmpty();
    }

    public boolean isTitleBlank() {
        return title == null || title.trim().isEmpty();
    }
}
