package ru.skypro.homework.dto.ads;

import lombok.Data;
import java.util.List;
@Data
public class ResponseWrapperAds {
    private int count;
    private List<ResponseAd> results;

    public ResponseWrapperAds() {
    }

}
