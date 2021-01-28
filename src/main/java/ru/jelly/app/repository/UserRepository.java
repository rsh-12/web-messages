package ru.jelly.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jelly.app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
