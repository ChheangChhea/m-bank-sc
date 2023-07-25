package com.example.mbank.api.user;

import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Iterable<UserDto> findAll();

    UserDto createNew(CreateUserDto createUserDto);
    void createUserRoles(Integer userId,List<Integer> roleIds);

    UserDto findById(Integer id);



    UserDto findByUuid(String uuid);

}
