package com.example.mbank.api.user;

import com.example.mbank.api.auth.Role;
import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    public Iterable<UserDto> findAll() {

        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> log.info("user:{}"
                , user.getUserRoles().get(0).getRole().getName()));


        return userMapper.userToUserDto(users);

    }

    @Override
    public UserDto findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDto(user);

    }

    @Override
    public UserDto createNew(CreateUserDto createUserDto) {

        User newUser = userMapper.createUserDtoToUser(createUserDto);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setIsStudent(false);
        newUser.setIsDelete(false);
        newUser.setIsVerified(true);

        userRepository.save(newUser);


        List<UserRole> userRoles = new ArrayList<>();

        createUserDto.roleIds().forEach(id ->
                userRoles.add(UserRole.builder()
                        .user(newUser)
                        .role(Role.builder().id(id).build())
                        .build()));
        userRoleRepository.saveAll(userRoles);

        System.out.println(newUser.getId());
//        newUser =userRepository.
        System.out.println();
        return findById(newUser.getId());
    }


}
