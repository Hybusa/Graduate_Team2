package ru.skypro.homework.dto.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserGet {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;

    public UserGet(Long id, String email, String firstName, String lastName, String phone, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.image = image;
    }
}
