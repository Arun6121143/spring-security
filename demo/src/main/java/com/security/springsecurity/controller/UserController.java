package com.security.springsecurity.controller;

import com.security.springsecurity.model.User;
import com.security.springsecurity.service.UserService;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/registerUser")
    public String registerUser(@RequestBody final User user) {

        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public  String loginUser(@RequestBody final User user) throws NoSuchAlgorithmException {
        return userService.verifyUser(user);
    }
}
