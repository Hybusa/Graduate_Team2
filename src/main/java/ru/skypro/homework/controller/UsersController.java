package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.LoginReq;
import ru.skypro.homework.model.User;

@RestController
@RequestMapping("users")
public class UsersController {

    @PostMapping("set_password")
    public ResponseEntity setPassword(@RequestBody LoginReq loginReq){
        //TODO Complete the method
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<User> getUser(){
        User user = new User();
        //TODO Complete the method
        return ResponseEntity.ok(user);
    }

    @PatchMapping("me")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        //TODO Complete the method
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestBody MultipartFile image){
        //TODO Complete the method
        return ResponseEntity.ok("OK");
    }
}
