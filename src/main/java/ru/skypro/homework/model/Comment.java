package ru.skypro.homework.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class Comment {

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    LocalDateTime createdAt;
    int pk; //НЕПОНЯТНО

    String text;
}
