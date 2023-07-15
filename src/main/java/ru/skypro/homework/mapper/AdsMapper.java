package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ads.CreateOrUpdateAds;
import ru.skypro.homework.dto.ads.ResponseAd;
import ru.skypro.homework.dto.ads.ResponseFullAd;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.model.Ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AdsMapper {

    public static Ad createOrUpdateAdsToAd(Ad ad, CreateOrUpdateAds updateAd){
        ad.setDescription(updateAd.getDescription());
        ad.setPrice(updateAd.getPrice());
        ad.setTitle(updateAd.getTitle());
        return ad;
    }

    public static ResponseAd adToResponseAd(Ad ad){
        return new ResponseAd(
                ad.getAuthor().getId(),
                "/images/" + ad.getImage().getFileName(),
                ad.getId(),ad.getPrice(),
                ad.getTitle()
        );
    }

    public static ResponseFullAd adToResponseFullAd(Ad ad){
        return new ResponseFullAd(
                ad.getId(),
                ad.getAuthor().getFirstName(),
                ad.getAuthor().getLastName(),
                ad.getDescription(),
                ad.getAuthor().getEmail(),
                ("/images/" + ad.getImage().getFileName()),
                ad.getAuthor().getPhone(),
                ad.getPrice(),
                ad.getTitle()
        );
    }

    public static ResponseWrapperAds adsToResponseWrapperAds(List<Ad> ads){
        ResponseWrapperAds results = new ResponseWrapperAds();
        results.setCount(ads.size());
        List<ResponseAd> responseAds= new ArrayList<>();
        for (Ad ad : ads) {
            responseAds.add(AdsMapper.adToResponseAd(ad));
        }
        Collections.shuffle(responseAds);
        results.setResults(responseAds);
        return results;
    }
}
