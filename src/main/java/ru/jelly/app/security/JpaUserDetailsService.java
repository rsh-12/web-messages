package ru.jelly.app.security;
/*
 * Date: 1/28/21
 * Time: 8:06 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jelly.app.entity.User;
import ru.jelly.app.repository.UserRepository;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Username not found");

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(s);

        return new SecurityUser(user);
    }
}
