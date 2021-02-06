package ru.jelly.app.service.impl;
/*
 * Date: 1/31/21
 * Time: 9:13 PM
 * */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.jelly.app.entity.User;
import ru.jelly.app.repository.UserRepository;
import ru.jelly.app.service.UserService;

import java.util.function.Supplier;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException("Username not found");

        log.info("> get the user by username: {}", username);
        return userRepository.findUserByUsername(username)
                .orElseThrow(supplier);
    }

    @Override
    public String registerUser(User candidate, BindingResult bindingResult, Model model) {

        boolean errors = isFieldHasErrors(candidate, bindingResult, model);
        if (errors) {
            return "security/registration";
        }

        encodeAndSave(candidate);
        return "redirect:/";
    }


    /* Helper methods */
    private void encodeAndSave(User candidate) {
        candidate.setPassword(bCryptPasswordEncoder.encode(candidate.getPassword()));
        userRepository.save(candidate);
    }

    private boolean isFieldHasErrors(User candidate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return true;
        }

        if (!candidate.getPassword().equals(candidate.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return true;
        }

        if (userRepository.existsByUsername(candidate.getUsername())) {
            model.addAttribute("usernameError", "A user with the same name already exists");
            return true;
        }
        return false;
    }
}
