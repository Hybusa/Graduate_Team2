package ru.skypro.homework.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "postId")
    long postId;
    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
    //private User user;
    LocalDateTime createdAt;
    int pk; //НЕПОНЯТНО
    String text;
}
