package com.security.springsecurity.service;

import com.security.springsecurity.model.User;
import com.security.springsecurity.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        log.info("came to loadUserByUsername");
        User user = userRepo.findByUsername(username);

        if (user == null) {
            log.info("User Not Found. Invalid User");
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetail(user);
    }
}
