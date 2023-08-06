package co.istad.mbank.api.user;

import co.istad.mbank.api.user.web.CreateUserDto;
import co.istad.mbank.api.user.web.UpdateUserDto;
import co.istad.mbank.api.user.web.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserDto userToUserDto(User user);

    Iterable <UserDto> userToUserDto(Iterable<User> users);

    User createUserDtoToUser(CreateUserDto createUserDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDtoToAccount(UpdateUserDto updateUserDto, @MappingTarget User user);
}
