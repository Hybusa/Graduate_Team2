package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ads> addAds(@RequestPart("image") MultipartFile image,
                                      @RequestPart("properties") CreateAds createAds) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("ad_pk") String adPk) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("ad_pk") String adPk,
                                              @RequestBody Comment comment) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getFullAd(@PathVariable("id") int id) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAds(@PathVariable("id") int id) {
        //TODO Complete the method
        // Return a 204 No Content response
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable("id") int id,
                                         @RequestBody CreateAds updatedAds) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getComments(@PathVariable("ad_pk") String adPk,
                                               @PathVariable("id") int id) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("ad_pk") String adPk,
                                               @PathVariable("id") int id) {
        //TODO Complete the method
        // Return a 200 OK response
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> updateComments(@PathVariable("ad_pk") String adPk,
                                                  @PathVariable("id") int id,
                                                  @RequestBody Comment updatedComment) {
        //TODO Complete the method
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
