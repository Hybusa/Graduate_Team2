package ru.skypro.homework.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comments.CommentString;
import ru.skypro.homework.dto.comments.ResponseComment;
import ru.skypro.homework.dto.comments.ResponseWrapperComments;
import ru.skypro.homework.mapper.mapStruct.CommentsMapperMapStruct;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final AdsService adsService;
    private final UserService userService;
    private final CommentsMapperMapStruct commentsMapperMapStruct;

    public CommentService(CommentsRepository commentsRepository, AdsService adsService, UserService userService, CommentsMapperMapStruct commentsMapperMapStruct) {
        this.commentsRepository = commentsRepository;
        this.adsService = adsService;
        this.userService = userService;
        this.commentsMapperMapStruct = commentsMapperMapStruct;
    }

    public String getCommentAuthorNameByCommentId(Long id){
       return commentsRepository.findById(id).map(com -> com.getUser().getEmail()).orElseThrow(RuntimeException::new);
    }

    public Optional<ResponseComment> createComment(Long id, CommentString commentString, String login){
        Comment comment = new Comment();
        Optional<Ad> adOptional = adsService.getAdOptionalById(id);
        if(adOptional.isEmpty()) {
            return Optional.empty();
        }
        Optional<User> userOptional = userService.getUserByLogin(login);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found!");
        }

        comment.setAd(adOptional.get());
        comment.setUser(userOptional.get());
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(commentString.getText());

        return Optional.of(commentsMapperMapStruct.commentToResponseComment(commentsRepository.save(comment)));
    }

    public ResponseWrapperComments getAllAdComments(Long id) {
        List<ResponseComment> responseCommentList = new ArrayList<>();
        List<Comment> commentList = commentsRepository.findAllByAdId(id);
        for (Comment comment : commentList) {
            responseCommentList.add(commentsMapperMapStruct.commentToResponseComment(comment));
        }
        return commentsMapperMapStruct.commentsToResponseWrapperComments(responseCommentList);
    }

    @PreAuthorize("hasRole('ADMIN') " +
            "OR authentication.name == @commentService.getCommentAuthorNameByCommentId(#commentId)")
    public boolean deleteAdComment(Long adId, Long commentId) {
        if(!commentsRepository.existsById(commentId)) {
            return false;
        }
        commentsRepository.deleteById(commentId);
        return true;
    }

    @PreAuthorize("hasRole('ADMIN') " +
            "OR authentication.name == @commentService.getCommentAuthorNameByCommentId(#commentId)")
    public Optional<ResponseComment> updateComment(Long adId, Long commentId, CommentString updatedComment) {
        Optional<Comment> commentOptional = commentsRepository.findById(commentId);
        if(commentOptional.isEmpty()) {
            return Optional.empty();
        }
        Comment comment = commentOptional.get();
        comment.setText(updatedComment.getText());
       return Optional.of(commentsMapperMapStruct.commentToResponseComment(commentsRepository.save(comment)));
    }
}
