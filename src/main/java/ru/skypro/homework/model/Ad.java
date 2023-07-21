package ru.skypro.homework.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.skypro.homework.dto.ads.CreateOrUpdateAds;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
public class Ad {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name ="image_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Image image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private String title;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Comment> adsComments;
    
    public Ad() {}
    public Ad(User user, CreateOrUpdateAds newAd){
        this.author = user;
        this.price = newAd.getPrice();
        this.title = newAd.getTitle();
        this.description = newAd.getDescription();
    }
}
