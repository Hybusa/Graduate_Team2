package ru.skypro.homework.dto.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.model.Role;

@Data
@RequiredArgsConstructor
public class UserGet {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;

    public UserGet(Long id, String email, String firstName, String lastName, String phone, Role role, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role=role.name();
        this.image = image;
    }
}
