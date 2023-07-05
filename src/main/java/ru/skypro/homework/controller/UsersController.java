package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequestMapping("users")
@CrossOrigin(value = "http://localhost:3000/")
public class UsersController {

    @PostMapping("set_password")
    public ResponseEntity<String> setPassword(/*@RequestBody LoginReq loginReq*/){
        //TODO Complete the method
        return ResponseEntity.ok("set_password");
    }

    @GetMapping("me")
    public ResponseEntity<String> getUser(/*@RequestBody User user*/){

        //TODO Complete the method
        return ResponseEntity.ok("get_me");
    }

    @PatchMapping("me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        //TODO Complete the method
        return ResponseEntity.ok(new UserDto());
    }

    @PatchMapping(value = "me/image"/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public ResponseEntity<String> updateUserImage(/*@RequestBody MultipartFile image*/){
        //TODO Complete the method
        return ResponseEntity.ok("OK");
    }
}
