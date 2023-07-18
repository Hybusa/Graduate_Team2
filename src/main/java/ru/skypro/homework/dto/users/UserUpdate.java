package ru.skypro.homework.dto.users;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserUpdate {
    private String firstName;
    private String lastName;
    private String phone;

    public UserUpdate(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
