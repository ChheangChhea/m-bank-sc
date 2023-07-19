package com.example.mbank.init;

import com.example.mbank.api.auth.Authority;
import com.example.mbank.api.auth.AuthorityRepository;
import com.example.mbank.api.auth.RoleRepository;
import com.example.mbank.api.user.User;
import com.example.mbank.api.user.UserRepository;
import com.example.mbank.api.auth.Role;
import com.example.mbank.api.user.UserRole;
import com.example.mbank.api.user.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class DataInitialization {


    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @PostConstruct
    public void init() {


        Authority userRead = Authority.builder().name("user:read").build();
        Authority userWrite = Authority.builder().name("user:write").build();
        Authority userDelete = Authority.builder().name("user:Delete").build();
        Authority userUpdate = Authority.builder().name("user:update").build();

        Authority accountRead = Authority.builder().name("account:read").build();
        Authority accountWrite = Authority.builder().name("account:write").build();
        Authority accountDelete = Authority.builder().name("account:Delete").build();
        Authority accountUpdate = Authority.builder().name("account:update").build();

        Authority transactionRead = Authority.builder().name("transaction:read").build();
        Authority transactionWrite = Authority.builder().name("transaction:write").build();
        Authority transactionDelete = Authority.builder().name("transaction:Delete").build();
        Authority transactionUpdate = Authority.builder().name("transaction:update").build();

        List<Authority> authorities = List.of(
                userRead, userWrite, userDelete, userUpdate,
                accountRead, accountWrite, accountDelete, accountUpdate,
                transactionRead, transactionWrite, transactionDelete, transactionUpdate
        );
        authorityRepository.saveAll(authorities);

        Role roleAdmin = Role.builder()
                .name("ADMIN")
                .authorities(authorities)
                .build();

        Role roleManager = Role.builder()
                .name("MANAGER")
                .authorities(
                        List.of(
                                userRead, userDelete, userUpdate,
                                accountRead, accountDelete, accountUpdate,
                                transactionRead, transactionDelete, transactionUpdate
                        )
                )
                .build();

        Role roleCustomer = Role.builder()
                .name("CUSTOMER")
                .authorities(
                        List.of(
                                userRead, userWrite, userUpdate,
                                accountRead, accountWrite, accountUpdate,
                                transactionRead, transactionWrite
                        )
                )

                .build();
        List<Role> roles = List.of(
                roleAdmin, roleManager,roleCustomer
        );
        roleRepository.saveAll(roles);
        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .name("Administrator")
                .email("Chhea00@123")
                .gender("Male")
                .phoneNumber("0978216852")
                .isVerifyCode(true)
                .isDelete(false)
                .isStudent(false)
//                .userRoles(List.of(userRoleAdmin))
                .build();
        userRepository.save(user);

        UserRole userRoleAdmin = UserRole.builder()
                .user(user)
                .role(roleAdmin)
                .build();

        userRoleRepository.save(userRoleAdmin);


    }
}
