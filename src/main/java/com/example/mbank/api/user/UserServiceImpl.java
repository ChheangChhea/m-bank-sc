package com.example.mbank.api.user;

import com.example.mbank.api.auth.Role;
import com.example.mbank.api.user.web.CreateUserDto;
import com.example.mbank.api.user.web.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

//    private final EntityManager entityManager;



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
        log.info("User: {}", user.getUserRoles());
        return userMapper.userToUserDto(user);

    }
    @Override
    public UserDto findByUuid(String uuid) {

        return null;
    }
    @Override
    public UserDto createNew(CreateUserDto createUserDto) {

        User newUser = userMapper.createUserDtoToUser(createUserDto);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setIsStudent(false);
        newUser.setIsDelete(false);
        newUser.setIsVerified(true);

        newUser = userRepository.save(newUser);

        createUserRoles(newUser.getId(), createUserDto.roleIds());

         userRepository.refresh(newUser);

         userRepository.refresh(newUser);

//        return userMapper.userToUserDto(newUser);

        return findById(newUser.getId());

    }

    @Override
    public void createUserRoles(Integer userId, List<Integer> roleIds) {
        roleIds.forEach(id -> {
            UserRole userRole = UserRole.builder()
                    .user(User.builder().id(userId).build())
                    .role(Role.builder().id(id).build())
                    .build();
            userRoleRepository.save(userRole);
            userRoleRepository.refresh(userRole);

        });

    }
}
