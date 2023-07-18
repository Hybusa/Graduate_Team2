package ru.skypro.homework.dto.comments;

import lombok.Data;

@Data
public class ResponseComment {
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Long pk;
    private String text;

    public ResponseComment(Long author, String authorImage, String authorFirstName, Long createdAt, Long pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }
}
