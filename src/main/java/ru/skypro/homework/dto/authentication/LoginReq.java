package ru.skypro.homework.dto.authentication;

import lombok.Data;

@Data
public class LoginReq {
    private String password;
    private String username;
}
