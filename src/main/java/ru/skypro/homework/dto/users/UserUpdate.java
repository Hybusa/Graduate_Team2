package ru.skypro.homework.dto.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.User;
@Data
@NoArgsConstructor
public class UserUpdate {
    private String firstName;
    private String lastName;
    private String phone;



    public UserUpdate(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
    }
}
