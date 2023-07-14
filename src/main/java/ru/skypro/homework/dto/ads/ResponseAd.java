
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

    public ResponseAd(Long author, String image, Long pk, int price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }
}
