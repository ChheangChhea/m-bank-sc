package com.example.mbank.api.user.web;


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
        System.out.println(usersDto);
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" Users  have been found")
                .dateTime(LocalDateTime.now())
                .payload(usersDto)
                .build();
    }

    @GetMapping("/{uuid}")
    public BaseApi<?> findByUuid(@PathVariable String uuid) {

        var userDto = userService.findByUuid(uuid);

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

        UserDto newUser = userService.createNew(createUserDto);
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" User  has been created")
                .dateTime(LocalDateTime.now())
                .payload(newUser)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid) {

        userService.deleteByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    public void disableUuid(@PathVariable String uuid){
        userService.disableByUuid(uuid);

    }

    @PutMapping("/{uuid}")
    public BaseApi<?> updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpdateUserDto updateUserDto ){
      UserDto updatedUser= userService.updateByUuid(uuid,updateUserDto);
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" User  has been found")
                .dateTime(LocalDateTime.now())
                .payload(updatedUser)
                .build();

    }
}
