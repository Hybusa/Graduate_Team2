package ru.skypro.homework.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.users.UserGet;
import ru.skypro.homework.dto.users.UserUpdate;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapperMapStruct {

    @Mapping(target = "image", expression = "java(user.getImage() == null? null: (\"/images/\" + user.getImage().getFileName()))")
    UserGet userToUserGet(User user);

    UserUpdate userToUpdateUser(User user);

    default User userUpdateToUser(User user, UserUpdate userUpdate){
        user.setFirstName(userUpdate.getFirstName().toUpperCase());
        user.setLastName(userUpdate.getLastName().toUpperCase());
        user.setPhone(user.getPhone());
        return user;
    }
}
