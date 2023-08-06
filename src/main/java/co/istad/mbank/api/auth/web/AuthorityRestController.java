package co.istad.mbank.api.auth.web;

import co.istad.mbank.api.auth.Role;
import co.istad.mbank.api.auth.RoleRepository;
import co.istad.mbank.base.BaseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authorities")
@RequiredArgsConstructor
public class AuthorityRestController {


    private  final RoleRepository roleRepository;
    @GetMapping
    public BaseApi<?>findAuthorities(){


        List<Role> roles = roleRepository.findAll();
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" Users  have been found")
                .dateTime(LocalDateTime.now())
                .payload(roles)
                .build();

    }



}
