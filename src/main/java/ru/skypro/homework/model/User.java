package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private LocalDate regDate;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private String role;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<Ad> userAds;

    public User(String email, String firstName, String lastName, String password, String phone,
                LocalDate regDate, Image image, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.regDate = regDate;
        this.image = image;
        this.role = role;
    }

    public User() {
    }

}
