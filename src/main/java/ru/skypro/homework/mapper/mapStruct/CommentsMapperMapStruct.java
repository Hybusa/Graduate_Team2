package ru.skypro.homework.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.comments.ResponseComment;
import ru.skypro.homework.dto.comments.ResponseWrapperComments;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.impl.CommentService;

import java.util.List;

@Mapper(componentModel="spring", uses= CommentService.class)
public interface CommentsMapperMapStruct {

    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "authorImage", expression  = "java(comment.getUser().getImage() == null? null: (\"/images/\" + comment.getUser().getImage().getFileName()))")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "id", target = "pk")
    ResponseComment commentToResponseComment(Comment comment);

    @Mapping(expression = "java(results.size())", target = "count")
    default ResponseWrapperComments commentsToResponseWrapperComments(List<ResponseComment> results){
        return adsToResponseWrapperAds(results.size(),results);
    }

    ResponseWrapperComments adsToResponseWrapperAds(int count, List<ResponseComment> results);
}
