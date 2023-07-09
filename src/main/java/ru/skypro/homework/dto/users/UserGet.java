package ru.skypro.homework.dto.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.model.User;

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
        if(user.getImage()!=null)
            this.image = "\\" + user.getImage().getFilePath();
        else
            this.image = null;
    }
}
