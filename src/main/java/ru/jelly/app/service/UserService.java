package ru.jelly.app.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.jelly.app.entity.User;

public interface UserService {
    User findByUsername(String username);

    String registerUser(User candidate, BindingResult bindingResult, Model model);
}
