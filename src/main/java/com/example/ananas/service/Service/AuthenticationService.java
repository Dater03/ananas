package com.example.ananas.service.Service;

import com.example.ananas.dto.request.AuthenticationRequest;
import com.example.ananas.dto.response.AuthenticationResponse;
import com.example.ananas.entity.User;
import com.example.ananas.exception.AppException;
import com.example.ananas.exception.ErrException;
import com.example.ananas.repository.User_Repository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationService {
    User_Repository userRepository;
    PasswordEncoder passwordEncoder;

    protected static final String KEY_SIGN = "lQgnbki8rjdh62RZ2FNXZB9KWYB1IjajiY04z011BXjjagnc7a";

    String createToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256); // xac dinh header cua token
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder() // noidung gui di cua token
                .subject(user.getUsername())
                .issuer("Ananas") // dinh dang nguoi tao ra token
                .issueTime(new Date()) // tgian tao hien tai
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() // thoi han dung
                ))
                .claim("scope",buildScopeToRoles(user)) // get role
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject()); // xac dinh thong tin cua token
        JWSObject jwsObject = new JWSObject(header, payload); // xac dinh token

        try {
            jwsObject.sign(new MACSigner(KEY_SIGN.getBytes())); // xac dinh signature cua token
            return jwsObject.serialize(); // tra ve token da duoc ky
        } catch (JOSEException e) {
            log.error("cant create token", e); // @Slf4j
            throw new RuntimeException();
        }
    }

    public AuthenticationResponse authenticationResponse(AuthenticationRequest authenticationRequest){
        var user = userRepository.findByUsername(authenticationRequest.getEmail())
                .orElseThrow(() -> new AppException((ErrException.EMAIL_NOT_EXISTED)));
        boolean checked = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if(!checked){
            throw new AppException(ErrException.USER_NOT_EXISTED);
        }
        var token = createToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .check(true)
                .build();
    }

    public String buildScopeToRoles(User user){
        StringJoiner stringJoiner = new StringJoiner(" "); // chuyển đổi ds các vai trò thành một chuỗi duy nhất, được cách nhau bằng khoảng trắng.
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(stringJoiner::add); // add cac vai tro tim duoc
        return stringJoiner.toString(); // ex: có các vai trò "ADMIN", "USER", thì hàm sẽ trả về chuỗi: "ADMIN USER"
    }
}
