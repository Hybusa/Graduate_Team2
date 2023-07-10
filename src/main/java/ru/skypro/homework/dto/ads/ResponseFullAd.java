package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.Ad;

@Data
public class ResponseFullAd {
    private Long pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;

    public ResponseFullAd(Ad ad) {
        this.pk = ad.getId();
        this.authorFirstName = ad.getAuthor().getFirstName();
        this.authorLastName = ad.getAuthor().getLastName();
        this.description = ad.getDescription();
        this.email = ad.getAuthor().getEmail();
        //TODO Complete directory methods
        this.image = "\\" + ad.getImage().getFilePath();
        this.phone = ad.getAuthor().getPhone();
        this.price = ad.getPrice();
        this.title = ad.getTitle();
    }
}
