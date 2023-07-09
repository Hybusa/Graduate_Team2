package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsService {
    private final UserService userService;
    private final AdsRepository adsRepository;

    public AdsService(UserService userService, AdsRepository adsRepository) {
        this.userService = userService;
        this.adsRepository = adsRepository;
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
}
