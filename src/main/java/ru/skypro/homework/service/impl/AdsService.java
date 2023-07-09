package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAds;
import ru.skypro.homework.dto.ResponseAd;
import ru.skypro.homework.dto.ResponseWrapperAds;
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
        if(userOptional.isEmpty())
            return new ResponseWrapperAds(0, new ArrayList<>());
        List<Ad> adsList = userOptional.get().getUserAds();
        return new ResponseWrapperAds(adsList.size(),adsList);
    }

    public ResponseAd createOrUpdateAd(String login, MultipartFile image, CreateOrUpdateAds createOrUpdateAds) {
        Optional<User> userOptional = userService.getUserByLogin(login);
        if (userOptional.isEmpty())
            throw new RuntimeException("User not found!");

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
}
