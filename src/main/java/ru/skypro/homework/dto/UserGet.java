package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.Base64;

@Data
@RequiredArgsConstructor
public class UserGet {
    Long id;
    String email;
    String firstName;
    String lastName;
    String phone;
    String image;

    public UserGet(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        if(user.getImage()!=null) {
            this.image = "\\" +user.getImage().getFilePath();
        }
        else
            this.image = null;
    }
}
