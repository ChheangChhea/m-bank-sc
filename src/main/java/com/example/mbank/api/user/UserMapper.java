package com.example.mbank.api.user;

import com.example.mbank.api.user.web.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserDto userToUserDto(User user);

    Iterable <UserDto> userToUserDto(Iterable<User> users);

//    User createUserToUserDto(User new)

}
