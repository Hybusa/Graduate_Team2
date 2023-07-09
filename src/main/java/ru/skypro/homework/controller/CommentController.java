package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateAds;
import ru.skypro.homework.dto.ResponseWrapperComments;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {
    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperComments> getAllUserAds(@PathVariable("id") Long id) {
        //TODO Complete the method
        return  ResponseEntity.ok(new ResponseWrapperComments(0,new ArrayList<>()));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<String> addCommentToAd(@PathVariable("id") Long id) {
        //TODO Complete the method
        return ResponseEntity.ok("Comment");
    }

    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<?> deleteAdComment(@PathVariable("adId") Long adId, @PathVariable("commentsId") Long commentId) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity<String> patchAdComment(@PathVariable("adId") Long adId,
                                                  @PathVariable("commentsId") Long commentId,
                                                  @RequestBody CreateOrUpdateAds updatedAds) {
        //TODO Complete the method
        return ResponseEntity.ok("Comment");
    }

}
