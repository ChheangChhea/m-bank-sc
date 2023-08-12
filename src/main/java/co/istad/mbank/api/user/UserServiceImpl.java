package co.istad.mbank.api.user;

import co.istad.mbank.api.user.web.CreateUserDto;
import co.istad.mbank.api.user.web.UpdateUserDto;
import co.istad.mbank.api.user.web.UserDto;
import co.istad.mbank.api.auth.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Transactional
@Slf4j
@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Iterable<UserDto> findAll() {

        Iterable<User> users = userRepository.findAll();

        return userMapper.userToUserDto(users);

    }
    @Override
    public UserDto findById(Integer id) {

        User user = userRepository.findById(id).orElseThrow();

        return userMapper.userToUserDto(user);

    }

    @Override
    public UserDto findByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User UUID  : %s is not found", uuid)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public void deleteByUuid(String uuid) {
        if (userRepository.existsByUuid(uuid)) {

            userRepository.deleteByUuid(uuid);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User UUID  : %s is not found", uuid));

    }

    @Override
    public void disableByUuid(String uuid) {
        userRepository.updateIsDeletedByUuid(true, uuid);
    }


    @Override
    public UserDto createNew(CreateUserDto createUserDto) {
        User newUser = userMapper.createUserDtoToUser(createUserDto);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setPassword(passwordEncoder.encode("123"));
        newUser.setIsStudent(false);
        newUser.setIsDeleted(false);
        newUser.setIsVerified(true);
        newUser = userRepository.save(newUser);
        System.out.println(newUser);
        createUserRoles(newUser.getId(), createUserDto.roleIds());
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

    @Override
    public UserDto updateByUuid(String uuid, UpdateUserDto updateUserDto) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User UUID  : %s is not found", uuid))
                );
        userMapper.updateUserDtoToAccount(updateUserDto, user);
        System.out.println(user.getId());
//        userRoleRepository.delete(UserRole.builder().user(user).build());
        //logic
//        List<UserRole> userRoles = userRoleRepository.findByUser(user);

        userRoleRepository.deleteByUser(user);
        createUserRoles(user.getId(), updateUserDto.roleIds());
        user.setUserRoles(userRoleRepository.findByUser(user));
        userRepository.save(user);

        return userMapper.userToUserDto(user);
    }
}
