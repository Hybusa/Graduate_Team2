package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.comments.ResponseComment;
import ru.skypro.homework.dto.comments.ResponseWrapperComments;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;

import java.util.List;

public class CommentsMapper {
    public static ResponseComment CommentToResponseComment(Comment comment){
        Image image = comment.getUser().getImage();
        return new ResponseComment(
                image == null? null: ("\\" + image.getFilePath()),
                comment.getUser().getFirstName(),comment.getCreatedAt(),
                comment.getId(),
                comment.getText());
    }

    public static ResponseWrapperComments CommentsToResponseWrapperComments(List<ResponseComment> commentList){
        return new ResponseWrapperComments(commentList.size(), commentList);
    }

}
