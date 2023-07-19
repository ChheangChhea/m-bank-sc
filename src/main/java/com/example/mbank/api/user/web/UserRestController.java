package com.example.mbank.api.user.web;

import com.example.mbank.api.user.User;
import com.example.mbank.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    Iterable<User>finAll(){
        return userService.findAll();
    }


}
