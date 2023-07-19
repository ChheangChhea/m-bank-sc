package com.example.mbank.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
