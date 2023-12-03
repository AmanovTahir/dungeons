package ru.otus.dungeons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dungeons.domain.UserGame;

import java.util.Optional;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {
    Optional<UserGame> findByUsername(String userName);
}
