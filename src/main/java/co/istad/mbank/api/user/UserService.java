package co.istad.mbank.api.user;

import co.istad.mbank.api.user.web.CreateUserDto;
import co.istad.mbank.api.user.web.UpdateUserDto;
import co.istad.mbank.api.user.web.UserDto;
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
