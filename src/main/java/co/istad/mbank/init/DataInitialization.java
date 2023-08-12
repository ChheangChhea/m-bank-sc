package co.istad.mbank.init;

import co.istad.mbank.api.auth.Authority;
import co.istad.mbank.api.auth.AuthorityRepository;
import co.istad.mbank.api.auth.RoleRepository;
import co.istad.mbank.api.user.User;
import co.istad.mbank.api.user.UserRepository;
import co.istad.mbank.api.auth.Role;
import co.istad.mbank.api.user.UserRole;
import co.istad.mbank.api.user.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

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
                .password(passwordEncoder.encode("1234"))
                .gender("Male")
                .phoneNumber("0978216852")
                .isDeleted(false)
                .isVerified(true)
                .isStudent(false)
                .build();

        User customer = User.builder()
                .uuid(UUID.randomUUID().toString())
                .name("Customer")
                .email("Chhea01@123")
                .password(passwordEncoder.encode("1234"))
                .gender("Male")
                .phoneNumber("0978216852")
                .isDeleted(false)
                .isVerified(true)
                .isStudent(false)
                .build();
        userRepository.save(user);
        userRepository.save(customer);

        UserRole userRoleAdmin = UserRole.builder()
                .user(user)
                .role(roleAdmin)
                .build();

        UserRole userRoleManage = UserRole.builder()
                .user(user)
                .role(roleManager)
                .build();
        UserRole userRoleCustomer= UserRole.builder()
                .user(user)
                .role(roleCustomer)
                .build();


        userRoleRepository.save(userRoleAdmin);
        userRoleRepository.save(userRoleManage);
        userRoleRepository.save(userRoleCustomer);
        System.out.println(userRepository.findById(user.getId()).get().getUserRoles().get(0).getRole().getName());


    }
}
