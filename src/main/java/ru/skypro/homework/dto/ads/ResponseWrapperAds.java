package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.ArrayList;
import java.util.List;
@Data
public class ResponseWrapperAds {
    private int count;
    private List<ResponseAd> results;

    public ResponseWrapperAds(int count, List<Ad> ads) {
        this.count = count;
        List<ResponseAd> responseAds= new ArrayList<>();

        for (Ad ad : ads) {
            responseAds.add(new ResponseAd(ad));
        }
        this.results = responseAds;
    }

    public ResponseWrapperAds(List<Ad> ads){
        this.count = ads.size();
        List<ResponseAd> responseAds= new ArrayList<>();
        for (Ad ad : ads) {
            responseAds.add(new ResponseAd(ad));
        }
        this.results = responseAds;
    }
}
