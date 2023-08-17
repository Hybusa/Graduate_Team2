package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.authentication.NewPassword;
import ru.skypro.homework.dto.authentication.RegisterReq;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder passwordEncoder, UserService userService) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(registerReq.getPassword())
                        .username(registerReq.getUsername())
                        .roles(role.name())
                        .build()
        );
        userService.registerUser(registerReq, this.encoder.encode(registerReq.getPassword()), role);
        return true;
    }

    @Override
    public boolean changeUserPassword(String login, NewPassword newPassword) {
        if (!encoder.matches(newPassword.getCurrentPassword(), manager.loadUserByUsername(login).getPassword())) {
            return false;
        }
        Optional<ru.skypro.homework.model.User> userOptional = userService
                .updatePassword(login, this.encoder.encode(newPassword.getNewPassword()));
        if (userOptional.isEmpty()) {
            return false;
        }

        Role role = userOptional.get().getRole();
        manager.updateUser(User.builder()
                .passwordEncoder(this.encoder::encode)
                .password(newPassword.getNewPassword())
                .username(login)
                .roles(role.name())
                .build());
        return true;
    }
}
