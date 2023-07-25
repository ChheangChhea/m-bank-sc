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
        System.out.println(user.getUserRoles());
//        log.info("User: {}",user.getUserRoles().get(0).getRole().getName());
        log.info("User: {}", user.getUserRoles());
        return userMapper.userToUserDto(user);

    }

    @Override
    public Integer createNew(CreateUserDto createUserDto) {

        User newUser = userMapper.createUserDtoToUser(createUserDto);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setIsStudent(false);
        newUser.setIsDelete(false);
        newUser.setIsVerified(true);

        User savedUser = userRepository.saveAndFlush(newUser);

        createUserRoles(savedUser.getId(),createUserDto.roleIds());



//        System.out.println(userRoles.get(0).getRole().getName());
//        newUser =userRepository.
        /*System.out.println(newUser.getId());
       User insertedUser =userRepository.findById(newUser.getId()).orElseThrow();
        System.out.println(insertedUser.getUserRoles());*/
//        return findById(newUser.getId());
//        return findById(savedUser.getId());

        return newUser.getId();
    }

    @Override
    public void createUserRoles(Integer userId,List <Integer> roleIds) {

        List<UserRole> userRoles = new ArrayList<>();

        roleIds.forEach(id ->
                userRoles.add(UserRole.builder()
                        .user(User.builder().id(userId).build())
                        .role(Role.builder().id(id).build())
                        .build()));
        userRoleRepository.saveAll(userRoles);

    }
}
