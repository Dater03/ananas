package com.example.ananas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    protected static final String KEY_SIGN = "lQgnbki8rjdh62RZ2FNXZB9KWYB1IjajiY04z011BXjjagnc7a";

    private final String[] PUBLIC_POST_ENDPOINTS = {"/user","/authen/token"};
    private final String[] PUBLIC_PUT_ENDPOINTS = {"/upload/photo/{id}"};
    private final String[] PUBLIC_GET_ENDPOINTS = {"/user"};

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST,PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.PUT,PUBLIC_PUT_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
        );
        httpSecurity.oauth2ResourceServer(request -> request.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtD()))); // cho phep cac endpoint chay duoc khi token hop le
        httpSecurity.csrf(AbstractHttpConfigurer::disable); //vô hiệu hóa tính năng bảo vệ CSRF
        return httpSecurity.build();
    }

    @Bean
        // giai ma JWT va xac dinh token co hop le k
    JwtDecoder jwtD() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY_SIGN.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
        // chuyen SCOPE -> ROLE
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter grantedConverter = new JwtGrantedAuthoritiesConverter();
        grantedConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter authenConverter = new JwtAuthenticationConverter();
        authenConverter.setJwtGrantedAuthoritiesConverter(grantedConverter);
        return authenConverter;
    }
}
