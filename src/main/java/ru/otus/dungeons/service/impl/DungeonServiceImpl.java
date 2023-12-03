package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.repository.DungeonRepository;
import ru.otus.dungeons.service.DungeonService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DungeonServiceImpl implements DungeonService {
    private final DungeonRepository repository;

    @Override
    public Dungeon getDungeon(Long dungeonId) {
        return repository.getReferenceById(dungeonId);
    }

    @Override
    public List<Dungeon> getAll() {
        return repository.findAll();
    }
}
