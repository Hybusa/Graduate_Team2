package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comments.CommentString;
import ru.skypro.homework.dto.comments.ResponseComment;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;

    public CommentService(CommentsRepository commentsRepository, AdsRepository adsRepository) {
        this.commentsRepository = commentsRepository;
        this.adsRepository = adsRepository;
    }

    public Optional<ResponseComment> createComment(Long id, CommentString commentString){
        Comment comment = new Comment();
        Optional<Ad> adOptional = adsRepository.findById(id);
        if(adOptional.isEmpty())
            return Optional.empty();

        Date date = new Date();

        comment.setAd(adOptional.get());
        comment.setUser(comment.getAd().getAuthor());
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(commentString.getText());

        return Optional.of(new ResponseComment(commentsRepository.save(comment)));
    }

    public List<ResponseComment> getAllAdComments(Long id) {
        List<ResponseComment> responseCommentList = new ArrayList<>();
        List<Comment> commentList = commentsRepository.findAllByAdId(id);
        for (Comment comment : commentList) {
            responseCommentList.add(new ResponseComment(comment));
        }
        return responseCommentList;
    }

    public boolean deleteAdComment(Long adId, Long commentId) {
        if(!commentsRepository.existsById(commentId))
            return false;
        commentsRepository.deleteById(commentId);
        return true;
    }

    public Optional<ResponseComment> updateComment(Long adId, Long commentId, String updatedComment) {
        Optional<Comment> commentOptional = commentsRepository.findById(commentId);
        if(commentOptional.isEmpty())
            return Optional.empty();
        Comment comment = commentOptional.get();
        comment.setText(updatedComment);
       return Optional.of(new ResponseComment(commentsRepository.save(comment)));
    }
}
