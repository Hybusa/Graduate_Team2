package ru.skypro.homework.dto.comments;

import lombok.Data;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;

@Data
public class ResponseComment {
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Long pk;
    private String text;

    public ResponseComment(Comment comment) {
        Image image = comment.getUser().getImage();
        if (image != null)
            this.authorImage = "/" + image;
        this.authorFirstName = comment.getUser().getFirstName();
        this.createdAt = comment.getCreatedAt();
        this.pk = comment.getId();
        this.text = comment.getText();
    }
}
