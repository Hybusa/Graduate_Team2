package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ru.skypro.homework.dto.CreateOrUpdateAds;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
public class Ad {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User author;

    @OneToOne
    @JoinColumn(name ="image_id")
    private Image image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private String title;

    private String description;

    public Ad() {}
    public Ad(User user, CreateOrUpdateAds newAd){
        this.author = user;
        this.price = newAd.getPrice();
        this.title = newAd.getTitle();
        this.description = newAd.getDescription();
    }



}
