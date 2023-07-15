package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.authentication.RegisterReq;
import ru.skypro.homework.dto.users.UserGet;
import ru.skypro.homework.dto.users.UserUpdate;
import ru.skypro.homework.mapper.UsersMapper;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterReq registerReq, String encodedPassword, Role role) {
        userRepository.save(
                new User(
                        registerReq.getUsername(),
                        registerReq.getFirstName(),
                        registerReq.getLastName(),
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
                                .roles(Role.USER.name())
                                .build()
                        )
                );
        return result;
    }

    public Optional<UserUpdate> updateUserInfo(UserUpdate userUpdate, String login) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(login);
        return optionalUser.map(
                user -> UsersMapper.userToUpdateUser(
                        userRepository.save(UsersMapper.userUpdateToUser(user, userUpdate))
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
        return userOptional.map(UsersMapper::userToUserGet);
    }
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByEmailIgnoreCase(login);
    }
}
