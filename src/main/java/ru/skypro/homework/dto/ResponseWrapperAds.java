package ru.skypro.homework.dto;

import ru.skypro.homework.model.Ad;

import java.util.ArrayList;
import java.util.List;

public class ResponseWrapperAds {
    private int count;
    private List<ResponseAd> results;

    public ResponseWrapperAds() {
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResponseAd> getResults() {
        return results;
    }

    public void setResults(List<ResponseAd> results) {
        this.results = results;
    }
}
