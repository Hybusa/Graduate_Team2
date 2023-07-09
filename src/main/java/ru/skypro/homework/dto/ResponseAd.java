
package ru.skypro.homework.dto;

import ru.skypro.homework.model.Ad;

public class ResponseAd {

    Long author;
    String image;
    Long id;
    int price;
    String title;

    public ResponseAd(Ad ad){
        this.author = ad.getAuthor().getId();
        this.image = "\\" + ad.getImage().getFilePath();
        this.id = ad.getId();
        this.price = ad.getPrice();
        this.title = ad.getTitle();
    }
}
