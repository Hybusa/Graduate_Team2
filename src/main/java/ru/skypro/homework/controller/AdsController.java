package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.CreateOrUpdateAds;
import ru.skypro.homework.dto.ads.ResponseAd;
import ru.skypro.homework.dto.ads.ResponseFullAd;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.service.impl.AdsService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAd> addAds(@RequestPart("image") MultipartFile image,
                                             @RequestPart("properties") CreateOrUpdateAds adProperties)  {
        ResponseAd responseAd = adsService.createOrUpdateAd(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                image,
                adProperties);
        return  new ResponseEntity<>(responseAd,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseFullAd> getFullAd(@PathVariable("id") Long id) {
        Optional<ResponseFullAd> adOptional =  adsService.getResponseFullAd(id);
        return adOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Long id) {
        if(adsService.deleteAdById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseAd> updateAds(@PathVariable("id") Long id,
                                        @RequestBody CreateOrUpdateAds updatedAd) {
        Optional<ResponseAd> responseAdOptional = adsService.updateAd(id, updatedAd);
        return responseAdOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapperAds> getMyAds(){
        ResponseWrapperAds responseWrapperAds = adsService.getMyAds(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(responseWrapperAds);
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<?> updateAdImage(@PathVariable("id") Long id,
                                                  @RequestBody MultipartFile image) {
        Optional<String> responseStringOptional = adsService.updateAdImage(id,image);
        if(responseStringOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(responseStringOptional.get());
    }
}
