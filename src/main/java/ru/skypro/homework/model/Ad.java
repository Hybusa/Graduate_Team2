package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private int id;
    private int price;
    private String title;

    public Ad() {
    }

    public Ad(User author, Image image, int id, int price, String title) {
        this.author = author;
        this.image = image;
        this.id = id;
        this.price = price;
        this.title = title;
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

    public int getId() {
        return id;
    }

    public void setId(int pk) {
        this.id = pk;
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