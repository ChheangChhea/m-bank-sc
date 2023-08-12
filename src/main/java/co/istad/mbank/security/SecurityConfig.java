package co.istad.mbank.security;


import co.istad.mbank.util.KeyUtil;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final KeyUtil keyUtil;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;

    }
    @Bean
    public JwtAuthenticationProvider jwtAuthProvider(){
        JwtAuthenticationProvider auth=new JwtAuthenticationProvider(refreshTokenJwtDecoder());
        auth.setJwtAuthenticationConverter(jwtAuthenticationConverter());
        return auth;

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //security
        http.csrf(AbstractHttpConfigurer::disable);



        http.authorizeHttpRequests(auth -> {
           /* auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/api/v1/auth/**").hasAuthority("SCOPE_user:read");
            auth.requestMatchers(HttpMethod.POST,"/api/v1/auth/**").hasAuthority("SCOPE_user:write");
            auth.requestMatchers(HttpMethod.PUT,"/api/v1/auth/**").hasAuthority("SCOPE_user:update");
            auth.requestMatchers(HttpMethod.DELETE,"/api/v1/auth/**").hasAuthority("SCOPE_user:delete");
          */  auth.anyRequest()//.authenticated();
            .permitAll();
        });

        //Security Mechanism


        //HttpBasic Authentication (Stateless)

//        http.httpBasic(Customizer.withDefaults());

       // security Mechanism
        //jwt (stateless)
        http.oauth2ResourceServer(oauth2-> oauth2.jwt(
                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())
        ));


        // Configure STATELESS
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        return new JwtAuthenticationConverter();

    }
    @Primary
    @Bean(name = "accessTokenJwtDecoder")
    public JwtDecoder accessTokenJwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keyUtil.getAccessTokenPublicKey()).build();
    }
    @Bean(name = "refreshTokenJwtDecoder")
    public JwtDecoder refreshTokenJwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(
                keyUtil.getRefreshTokenPublicKey()).build();
    }
    @Primary
    @Bean(name = "accessTokenJwtEncoder")
    public NimbusJwtEncoder accessTokenJwtEncoder(){

        JWK jwk = new RSAKey.Builder(keyUtil.getAccessTokenPublicKey())
                .privateKey(keyUtil.getAccessTokenPrivateKey())
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet =new JWKSet(jwk);


        JWKSource<SecurityContext>jwkSource = (jwkSelector, context) -> jwkSelector.select(jwkSet);
        return new NimbusJwtEncoder(jwkSource);
    };

    @Bean(name = "refreshTokenJwtEncoder")
    public NimbusJwtEncoder refreshTokenJwtEncoder(){

        JWK jwk = new RSAKey.Builder(keyUtil.getRefreshTokenPublicKey())
                .privateKey(keyUtil.getRefreshTokenPrivateKey())
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet =new JWKSet(jwk);


        JWKSource<SecurityContext>jwkSource = (jwkSelector, context) -> jwkSelector.select(jwkSet);
        return new NimbusJwtEncoder(jwkSource);
    };

}
