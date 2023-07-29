package com.example.mbank.security;

import com.example.mbank.api.user.User;
import com.example.mbank.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=userRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() ->new UsernameNotFoundException("You with this email is not found"));

        CustomUserDetails customUserDetails=new CustomUserDetails();
        customUserDetails.setUser(user);

        return customUserDetails;
    }
}
