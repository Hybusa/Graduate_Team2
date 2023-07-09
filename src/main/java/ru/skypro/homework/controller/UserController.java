package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserGet;
import ru.skypro.homework.dto.UserUpdate;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.impl.ImageService;
import ru.skypro.homework.service.impl.UserService;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final ImageService imageService;

    public UserController(UserService userService, AuthService authService, ImageService imageService) {
        this.userService = userService;
        this.authService = authService;
        this.imageService = imageService;
    }


    @PostMapping("set_password")
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {

        if (authService.changeUserPassword(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), newPassword))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @GetMapping("me")
    public ResponseEntity<UserGet> getUser() {
        Optional<UserGet> userOptional = userService
                .getUserDtoByLogin(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                );

        return userOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PatchMapping("me")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdate userUpdate) {

        Optional<User> userOptional = userService.updateUserInfo(
                userUpdate,
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());

        return userOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());

    }

    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Image> updateUserImage(@RequestBody MultipartFile image) throws IOException {

        if(imageService.updateUserAvatar(image, SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()))
           return ResponseEntity.ok().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
