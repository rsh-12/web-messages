package ru.jelly.app.service;

import ru.jelly.app.entity.User;

public interface UserService {
    User findByUsername(String username);
}
