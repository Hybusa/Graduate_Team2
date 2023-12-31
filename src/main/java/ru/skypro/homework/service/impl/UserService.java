package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.authentication.RegisterReq;
import ru.skypro.homework.dto.users.UserGet;
import ru.skypro.homework.dto.users.UserUpdate;
import ru.skypro.homework.mapper.mapStruct.UserMapperMapStruct;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapperMapStruct userMapperMapStruct;

    public UserService(UserRepository userRepository, UserMapperMapStruct userMapperMapStruct) {
        this.userRepository = userRepository;
        this.userMapperMapStruct = userMapperMapStruct;
    }

    public void registerUser(RegisterReq registerReq, String encodedPassword, Role role) {
        userRepository.save(
                new User(
                        registerReq.getUsername(),
                        registerReq.getFirstName().toUpperCase(),
                        registerReq.getLastName().toUpperCase(),
                        encodedPassword,
                        registerReq.getPhone(),
                        LocalDate.now(),
                        null,
                        role));
    }

    public List<UserDetails> getUserDetails() {
        List<UserDetails> result = new ArrayList<>();
        userRepository.findAll()
                .forEach(u -> result.add(org.springframework.security.core.userdetails.User.builder()
                                .username(u.getEmail())
                                .password(u.getPassword())
                                .roles(u.getRole().name())
                                .build()
                        )
                );
        return result;
    }

    public Optional<UserUpdate> updateUserInfo(UserUpdate userUpdate, String login) {
        return userRepository.findByEmailIgnoreCase(login).map(
                user -> userMapperMapStruct.userToUpdateUser(
                        userRepository.save(userMapperMapStruct.userUpdateToUser(user, userUpdate))
                )
        );
    }

    public void updateUserImage(User user){
        userRepository.save(user);
    }

    public Optional<User> updatePassword(String login, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmailIgnoreCase(login);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();
        user.setPassword(newPassword);
        return Optional.of(userRepository.save(user));
    }

    public Optional<UserGet> getUserDtoByLogin(String login){
       Optional<User> userOptional =  userRepository.findByEmailIgnoreCase(login);
        return userOptional.map(userMapperMapStruct::userToUserGet);
    }
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByEmailIgnoreCase(login);
    }
}
