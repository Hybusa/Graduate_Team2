
package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.model.Ad;
@Data
public class ResponseAd {

    private Long author;
    private String image;
    private Long pk;
    private int price;
    private String title;

    public ResponseAd(Ad ad){
        this.author = ad.getAuthor().getId();
        this.image = "\\" + ad.getImage().getFilePath();
        this.pk = ad.getId();
        this.price = ad.getPrice();
        this.title = ad.getTitle();
    }
}
