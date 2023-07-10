package ru.skypro.homework.model;

import lombok.Data;
import lombok.ToString;
import ru.skypro.homework.dto.ads.CreateOrUpdateAds;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
public class Ad {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
   // @JsonBackReference
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
  //  @JsonManagedReference
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
