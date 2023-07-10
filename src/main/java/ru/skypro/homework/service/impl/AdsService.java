package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.CreateOrUpdateAds;
import ru.skypro.homework.dto.ads.ResponseAd;
import ru.skypro.homework.dto.ads.ResponseFullAd;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsService {
    private final UserService userService;
    private final AdsRepository adsRepository;
    private final ImageService imageService;

    public AdsService(UserService userService, AdsRepository adsRepository, ImageService imageService) {
        this.userService = userService;
        this.adsRepository = adsRepository;
        this.imageService = imageService;
    }


    public ResponseWrapperAds getAllAds(){
        return new ResponseWrapperAds(adsRepository.findAll());
    }

    public ResponseWrapperAds getMyAds(String login){
        Optional<User> userOptional = userService.getUserByLogin(login);
        if(userOptional.isEmpty()) {
            return new ResponseWrapperAds(0, new ArrayList<>());
        }
        List<Ad> adsList = userOptional.get().getUserAds();
        return new ResponseWrapperAds(adsList.size(),adsList);
    }

    public ResponseAd createOrUpdateAd(String login, MultipartFile image, CreateOrUpdateAds createOrUpdateAds) {
        Optional<User> userOptional = userService.getUserByLogin(login);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        //TODO Check if addUpdate;

        Ad newAd = adsRepository.save(new Ad(userOptional.get(), createOrUpdateAds));

        Image savedImage;
        try {
            savedImage = imageService.addAdImage(image, newAd.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newAd.setImage(savedImage);
        return new ResponseAd(adsRepository.save(newAd));
    }

    public Optional<ResponseFullAd> getResponseFullAd(Long id){
        Optional<Ad> adOptional = adsRepository.findById(id);
        ResponseFullAd responseFullAd;
        Ad ad;
        if(adOptional.isEmpty()) {
            return Optional.empty();
        }
        else{
            ad = adOptional.get();
            responseFullAd = new ResponseFullAd(ad);
        }
        return Optional.of(responseFullAd);

    }

    public boolean deleteAdById(Long id) {
        return adsRepository.existsById(id);
    }

    public Optional<ResponseAd> updateAd(Long id, CreateOrUpdateAds updatedAd) {
       Optional<Ad> adOptional = adsRepository.findById(id);
       if(adOptional.isEmpty()) {
           return Optional.empty();
       }
       Ad ad = adOptional.get();
       ad.setTitle(updatedAd.getTitle());
       ad.setPrice(updatedAd.getPrice());
       ad.setDescription(updatedAd.getDescription());

       return Optional.of(new ResponseAd(adsRepository.save(ad)));
    }

    public Optional<String> updateAdImage(Long id, MultipartFile image) {
        //TODO What string to return(for now it's path);

        Optional<Ad> adOptional = adsRepository.findById(id);
        if(adOptional.isEmpty()) {
            return Optional.empty();
        }
        Image newImage;
        try {
            newImage = imageService.addAdImage(image, id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.of("\\" + newImage.getFilePath());
    }
}
