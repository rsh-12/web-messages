package ru.jelly.app.controller;
/*
 * Date: 2/6/21
 * Time: 7:21 AM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jelly.app.entity.User;
import ru.jelly.app.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage(@ModelAttribute("user") User candidate,
                                      Principal principal) {
        return principal == null ? "security/registration" : "index";
    }

    @PostMapping
    public void registerUser(@Valid @ModelAttribute("user") User candidate,
                             BindingResult bindingResult, Model model) {
        userService.registerUser(candidate, bindingResult, model);
    }
}
