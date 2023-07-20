package com.example.mbank.api.user;

import com.example.mbank.api.auth.Role;
import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private  final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    public Iterable<UserDto> findAll() {

        Iterable<User> users = userRepository.findAll();
        return userMapper.userToUserDto(users);

    }
    @Override
    public UserDto findById(Integer id) {
        User user=userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDto(user);

    }
    @Override
    public UserDto createNew(CreateUserDto createUserDto) {

        User newUser=userMapper.createUserDtoToUser(createUserDto);
         newUser.setUuid(UUID.randomUUID().toString());
        newUser.setIsStudent(false);
         newUser.setIsDelete(false);
//         newUser.st

        userRepository.save(newUser);


        UserRole userRoleCustomer = UserRole.builder()
                .user(newUser)
                .role(Role.builder().id(3).build())
                .build();
        userRoleRepository.save(userRoleCustomer);

        return findById(newUser.getId());
    }


}
