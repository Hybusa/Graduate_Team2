package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comments.CommentString;
import ru.skypro.homework.dto.comments.ResponseComment;
import ru.skypro.homework.dto.comments.ResponseWrapperComments;
import ru.skypro.homework.service.impl.CommentService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperComments> getAllUserAds(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getAllAdComments(id));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<ResponseComment> addCommentToAd(@PathVariable("id") Long id,
                                                          @RequestBody CommentString comment) {
        Optional<ResponseComment> responseCommentOptional = commentService.createComment(id,
                comment,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return responseCommentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<?> deleteAdComment(@PathVariable("adId") Long adId, @PathVariable("commentsId") Long commentId) {
        if(commentService.deleteAdComment(adId,commentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<ResponseComment> patchAdComment(@PathVariable("adId") Long adId,
                                                 @PathVariable("commentsId") Long commentId,
                                                 @RequestBody CommentString updatedComment) {

        Optional<ResponseComment> responseCommentOptional = commentService.updateComment(adId, commentId, updatedComment);
        return responseCommentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
