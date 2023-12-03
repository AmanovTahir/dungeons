package ru.otus.dungeons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dungeons.domain.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
