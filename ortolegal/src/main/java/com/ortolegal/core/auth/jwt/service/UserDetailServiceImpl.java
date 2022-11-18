package com.ortolegal.core.auth.jwt.service;

import com.ortolegal.core.auth.jwt.data.UserDetail;
import com.ortolegal.user.model.UserModel;
import com.ortolegal.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = repository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + "not found");
        }

        return new UserDetail(user);
    }
}
