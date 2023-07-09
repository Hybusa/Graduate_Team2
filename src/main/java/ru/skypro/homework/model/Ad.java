package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.skypro.homework.dto.CreateOrUpdateAds;

import javax.persistence.*;

@Entity
@Table(name = "ads")
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

    public Ad() {
    }


    public Ad(User user, CreateOrUpdateAds newAd){
        this.author = user;
        this.price = newAd.getPrice();
        this.title = newAd.getTitle();
        this.description = newAd.getDescription();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
