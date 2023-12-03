package ru.otus.dungeons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dungeons.domain.UserCharacter;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
}
