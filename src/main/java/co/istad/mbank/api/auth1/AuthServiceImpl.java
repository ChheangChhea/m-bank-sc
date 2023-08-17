package co.istad.mbank.api.auth1;

import co.istad.mbank.api.auth1.web.AuthDto;
import co.istad.mbank.api.auth1.web.LoginDto;
import co.istad.mbank.api.auth1.web.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtEncoder jwtEncoder;
    private  JwtEncoder jwtRefreshEncoder;

    @Autowired
    @Qualifier("refreshTokenJwtEncoder")
    public void setJwtRefreshEncoder(JwtEncoder jwtRefreshEncoder) {

        this.jwtRefreshEncoder = jwtRefreshEncoder;
    }

    @Override
    public AuthDto login(LoginDto loginDto) {
        Authentication authentication =new UsernamePasswordAuthenticationToken(loginDto.email(),loginDto.password());
       authentication = daoAuthenticationProvider.authenticate(authentication);

     //  log.info("TEST : {}",authentication.getName());

        Instant now =Instant.now();
        String scope=authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
   //     log.info("SCOPE: {}",scope);


        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject("access-resources")
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .issuer("self")
                .issuedAt(now)
                .id(authentication.getName())
                .claim("scope",scope)
                .build();

        JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
                .subject("access-resources")
                .expiresAt(now.plus(2, ChronoUnit.DAYS))
                .issuer("self")
                .issuedAt(now)
                .id(authentication.getName())
                .claim("scope",scope)
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        String refreshToken= jwtRefreshEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaimsSet)).getTokenValue();

        return AuthDto.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthDto refresh(RefreshTokenDto refreshTokenDto) {

        Authentication authentication=new BearerTokenAuthenticationToken(refreshTokenDto.refreshToken());
       authentication = jwtAuthenticationProvider.authenticate(authentication);

        Instant now =Instant.now();
//        String scope=authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining("   "));
//        log.info("SCOPE: {}",scope);
        Jwt jwt = (Jwt) authentication.getCredentials();
       /* log.info("JWT: {}",jwt.getSubject());
        log.info("SCOPE: {}",jwt.getClaimAsStringList("scope"));*/

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject("access-resources")
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .issuer("self")
                .issuedAt(now)
                .id(authentication.getName())
                .claim("scope",jwt.getClaimAsStringList("scope"))
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        String refreshToken = refreshTokenDto.refreshToken();

        Duration duration = Duration.between(now, jwt.getExpiresAt());
    //    log.info("Remaining Days: {}", duration.toDays());

        if (duration.toDays() < 7) {
            JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
                    .subject("access-resources")
                    .expiresAt(now.plus(30, ChronoUnit.DAYS))
                    .issuer("self")
                    .issuedAt(now)
                    .id(authentication.getName())
                    .claim("scope", jwt.getClaimAsString("scope"))
                    .build();
            refreshToken = jwtRefreshEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaimsSet)).getTokenValue();
        }
        return AuthDto.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
