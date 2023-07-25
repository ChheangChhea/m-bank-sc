package com.example.mbank.api.user.web;

import com.example.mbank.api.user.User;
import com.example.mbank.api.user.UserService;
import com.example.mbank.base.BaseApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;


    @GetMapping
    public BaseApi<?> finAll() {

        var usersDto = userService.findAll();

        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" Users  have been found")
                .dateTime(LocalDateTime.now())
                .payload(usersDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseApi<?> findById(@PathVariable Integer id) {

        var userDto = userService.findById(id);

        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" User  has been found")
                .dateTime(LocalDateTime.now())
                .payload(userDto)
                .build();
    }
    @PostMapping
    public  BaseApi<?> createNew(@Valid @RequestBody CreateUserDto createUserDto){
       // UserDto newUserId = userService.createNew(createUserDto);
        Integer newUserId = userService.createNew(createUserDto);

        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" User  has been created")
                .dateTime(LocalDateTime.now())
//                .payload()
                .payload(userService.findById(2))
                .build();
    }

}
