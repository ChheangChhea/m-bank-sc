package com.example.mbank.api.user;

import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UpdateUserDto;
import com.example.mbank.api.user.web.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserDto userToUserDto(User user);

    Iterable <UserDto> userToUserDto(Iterable<User> users);

    User createUserDtoToUser(CreateUserDto createUserDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDtoToUser(UpdateUserDto updateUserDto,@MappingTarget User user);
}
