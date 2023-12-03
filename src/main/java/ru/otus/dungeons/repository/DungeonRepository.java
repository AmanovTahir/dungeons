package ru.otus.dungeons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dungeons.domain.Dungeon;

public interface DungeonRepository extends JpaRepository<Dungeon, Long> {
}
