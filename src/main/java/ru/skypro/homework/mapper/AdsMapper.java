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

    public static Ad CreateOrUpdateAdsToAd(Ad ad, CreateOrUpdateAds updateAd){
        ad.setDescription(updateAd.getDescription());
        ad.setPrice(updateAd.getPrice());
        ad.setTitle(updateAd.getTitle());
        return ad;
    }

    public static ResponseAd AdToResponseAd(Ad ad){
        return new ResponseAd(
                ad.getAuthor().getId(),
                "\\" + ad.getImage().getFilePath(),
                ad.getId(),ad.getPrice(),
                ad.getTitle()
        );
    }

    public static ResponseFullAd AdToResponseFullAd(Ad ad){
        return new ResponseFullAd(
                ad.getId(),
                ad.getAuthor().getFirstName(),
                ad.getAuthor().getLastName(),
                ad.getDescription(),
                ad.getAuthor().getEmail(),
                null,/*"\\" + ad.getImage().getFilePath()*/
                ad.getAuthor().getPhone(),
                ad.getPrice(),
                ad.getTitle()
        );
    }

    public static ResponseWrapperAds AdsToResponseWrapperAds (List<Ad> ads){
        ResponseWrapperAds results = new ResponseWrapperAds();
        results.setCount(ads.size());
        List<ResponseAd> responseAds= new ArrayList<>();
        for (Ad ad : ads) {
            responseAds.add(AdsMapper.AdToResponseAd(ad));
        }
        Collections.shuffle(responseAds);
        results.setResults(responseAds);
        return results;
    }
}
