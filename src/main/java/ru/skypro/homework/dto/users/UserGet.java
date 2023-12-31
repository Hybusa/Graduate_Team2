package ru.skypro.homework.dto.users;

import lombok.Data;

@Data
public class UserGet {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;
}
