package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.Dungeon;

import java.util.List;

public interface DungeonService {

    Dungeon getDungeon(Long id);

    List<Dungeon> getAll();

}
