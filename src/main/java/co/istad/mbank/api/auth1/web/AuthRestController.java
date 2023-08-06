package co.istad.mbank.api.auth1.web;

import co.istad.mbank.api.auth1.AuthService;
import co.istad.mbank.base.BaseApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final AuthService authService;
    @PostMapping("/login")
    public BaseApi<?>login(@Valid @RequestBody LoginDto loginDto){
    AuthDto authDto =authService.login(loginDto);
        return BaseApi.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .massage(" You login  has been successfully")
                .dateTime(LocalDateTime.now())
                .payload(authDto)
                .build();
    }

}
