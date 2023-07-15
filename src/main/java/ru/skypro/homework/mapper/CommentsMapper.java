package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comments.ResponseComment;
import ru.skypro.homework.dto.comments.ResponseWrapperComments;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;

import java.util.List;

public class CommentsMapper {
    public static ResponseComment commentToResponseComment(Comment comment){
        Image image = comment.getUser().getImage();
        return new ResponseComment(
                comment.getUser().getId(),
                image == null? null: ("/images/" + image.getFileName()),
                comment.getUser().getFirstName(),comment.getCreatedAt(),
                comment.getId(),
                comment.getText());
    }

    public static ResponseWrapperComments commentsToResponseWrapperComments(List<ResponseComment> commentList){
        return new ResponseWrapperComments(commentList.size(), commentList);
    }

}
