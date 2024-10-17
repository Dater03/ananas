package com.example.ananas.config;
//
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
@Configuration
public class SecurityConfig {
//    protected static final String KEY_SIGN = "lQgnbki8rjdh62RZ2FNXZB9KWYB1IjajiY04z011BXjjagnc7a";
//    private final String[] PUBLIC_POST_ENDPOINTS = {"/user","/authen/token", "/voucher/create"};
//    private final String[] PUBLIC_PUT_ENDPOINTS = {"/upload/photo/{id}"};
//    private final String[] PUBLIC_GET_ENDPOINTS = {"/user", "voucher/admin", "voucher/user"};
//
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(request ->
//                request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
//                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
//                        .anyRequest().authenticated()
//                );
//        http.oauth2ResourceServer(request -> request.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtD())));
//        http.csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }
//
//    @Bean
//    JwtDecoder jwtD() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY_SIGN.getBytes(), "HmacSHA256");
//        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//    }
//
//    @Bean
//    JwtAuthenticationConverter jwtConverter() {
//        JwtGrantedAuthoritiesConverter grantedConverter = new JwtGrantedAuthoritiesConverter();
//        grantedConverter.setAuthorityPrefix("ROLE_");
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(grantedConverter);
//        return converter;
//    }
}
