package ru.otus.dungeons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dungeons.domain.Monster;

import java.util.Optional;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    Optional<Monster> findFirstByName(String name);
}
