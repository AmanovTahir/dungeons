package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.exception.MonsterNotFoundException;
import ru.otus.dungeons.repository.MonsterRepository;
import ru.otus.dungeons.service.MonsterService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService {

    private final MonsterRepository monsterRepository;

    @Override
    public Monster getById(Long monsterId) {
        return monsterRepository.findById(monsterId)
                .orElseThrow(() -> new MonsterNotFoundException("No monsters found"));
    }

    @Override
    public List<Monster> findAll() {
        return monsterRepository.findAll();
    }

    @Override
    public void deleteById(Long monsterId) {
        monsterRepository.deleteById(monsterId);
    }

    @Override
    public Monster save(Monster monster) {
        return monsterRepository.save(monster);
    }
}
