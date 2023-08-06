package co.istad.mbank.api.auth1;

import co.istad.mbank.api.auth1.web.AuthDto;
import co.istad.mbank.api.auth1.web.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder jwtEncoder;

    @Override
    public AuthDto login(LoginDto loginDto) {
        Authentication authentication =new UsernamePasswordAuthenticationToken(loginDto.email(),loginDto.password());
       authentication = daoAuthenticationProvider.authenticate(authentication);

       log.info("TEST : {}",authentication.getName());
        Instant now =Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject("access-resources")
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .issuer("self")
                .issuedAt(now)
                .id(authentication.getName())
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();


        return AuthDto.builder()

                .tokenType("Bearer")
                .accessToken(accessToken)

                .build();
    }
}
