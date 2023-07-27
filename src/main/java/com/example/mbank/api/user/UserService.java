package com.example.mbank.api.user;

import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UpdateUserDto;
import com.example.mbank.api.user.web.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface UserService {
    Iterable<UserDto> findAll();

    UserDto createNew(CreateUserDto createUserDto);
    void createUserRoles(Integer userId,List<Integer> roleIds);

    UserDto findById(Integer id);


    UserDto findByUuid(String uuid);

    void deleteByUuid(String uuid);

    void disableByUuid(String uuid);

    UserDto updateByUuid(String uuid, UpdateUserDto updateUserDto);
}
