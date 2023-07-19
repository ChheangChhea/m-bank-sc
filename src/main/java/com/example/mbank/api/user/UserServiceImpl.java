package com.example.mbank.api.user;

import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;


@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private  final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Iterable<UserDto> findAll() {

        Iterable<User> users = userRepository.findAll();
//        List<UserDto> userDto = new ArrayList<>();

       // UserDto= StreamSupport.stream(users.spliterator(),true,)

        /*for (User user : users) {
            userDto.add(UserDto.builder()
                    .uuid(user.getUuid())
                    .name(user.getName())
                    .gender(user.getGender())
                    .email(user.getEmail())


                    .build());

        }*/
//        Iterable<User>

        return userMapper.userToUserDto(users);
    }

    @Override
    public UserDto createNew(CreateUserDto createUserDto) {
//        userRepository.save();
        return null;
    }
}
