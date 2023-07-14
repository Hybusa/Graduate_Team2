package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class ResponseWrapperAds {
    private int count;
    private List<ResponseAd> results;

    public ResponseWrapperAds() {
    }

    public ResponseWrapperAds(List<Ad> ads){
        this.count = ads.size();
        List<ResponseAd> responseAds= new ArrayList<>();
        for (Ad ad : ads) {
            responseAds.add(AdsMapper.AdToResponseAd(ad));
        }
        Collections.shuffle(responseAds);
        this.results = responseAds;
    }
}
