package ru.skypro.homework.dto.authentication;

import lombok.Data;

@Data
public class NewPassword {
    private String currentPassword;
    private String newPassword;
}
