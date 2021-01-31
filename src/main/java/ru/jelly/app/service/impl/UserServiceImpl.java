package ru.jelly.app.service.impl;
/*
 * Date: 1/31/21
 * Time: 9:13 PM
 * */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jelly.app.entity.User;
import ru.jelly.app.repository.UserRepository;
import ru.jelly.app.service.UserService;

import java.util.function.Supplier;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException("Username not found");

        log.info("> get the user by username: {}", username);
        return userRepository.findUserByUsername(username)
                .orElseThrow(supplier);
    }
}
