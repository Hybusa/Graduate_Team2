package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.users.UserGet;
import ru.skypro.homework.dto.users.UserUpdate;
import ru.skypro.homework.model.User;

public class UsersMapper {
    public static UserGet userToUserGet(User user){
        return new UserGet(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage() != null? ("/images/" + user.getImage().getFileName()):null
        );
    }

    public static UserUpdate userToUpdateUser(User user){
        return new UserUpdate(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone()
        );
    }

    public static User userUpdateToUser(User user, UserUpdate userUpdate){
        user.setFirstName(userUpdate.getFirstName().toUpperCase());
        user.setLastName(userUpdate.getLastName().toUpperCase());
        user.setPhone(user.getPhone());
        return user;
    }

}
