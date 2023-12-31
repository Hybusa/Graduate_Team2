package ru.skypro.homework.service;

import ru.skypro.homework.dto.authentication.NewPassword;
import ru.skypro.homework.dto.authentication.RegisterReq;
import ru.skypro.homework.model.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);
    boolean changeUserPassword(String name, NewPassword newPassword);
}
