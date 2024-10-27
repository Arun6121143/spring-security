package com.security.springsecurity.service;

import com.security.springsecurity.model.User;
import com.security.springsecurity.repository.UserRepo;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String saveUser(final User user) {
        try {
            User oldUser = userRepo.findByUsername(user.getUsername());

            if (oldUser == null) {
                User newUser = User.builder().id(user.getId())
                        .password(encoder.encode(user.getPassword())).username(user.getUsername())
                        .build();
                userRepo.save(newUser);
            } else {
                return "User Already Registered";
            }
        } catch (Exception e) {
            log.info("Exception While Registering The User", e);
        }

        return "User Registered Successfully";
    }

    public String verifyUser(final User user) throws NoSuchAlgorithmException {
        log.info("came here verify user");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "failure";
    }
}
