package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class ResponseFullAd {
    Long pk;
    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    String image;
    String phone;
    int price;
    String title;

    public ResponseFullAd(){}
    public ResponseFullAd(Ad ad){
        this.pk = ad.getId();
        this.authorFirstName = ad.getAuthor().getFirstName();
        this.authorLastName = ad.getAuthor().getLastName();
        this.description = ad.getDescription();
        this.email = ad.getAuthor().getEmail();
        this.image = "\\" + ad.getImage().getFilePath();
        this.phone = ad.getAuthor().getPhone();
        this.price = ad.getPrice();
        this.title = ad.getTitle();
    }
}
