package ru.skypro.homework.dto;

import java.util.List;

public class ResponseWrapperAds {
    private int count;
    private List<Ads> results;

    public ResponseWrapperAds() {
    }

    public ResponseWrapperAds(int count, List<Ads> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Ads> getResults() {
        return results;
    }

    public void setResults(List<Ads> results) {
        this.results = results;
    }
}
