package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.Monster;

import java.util.List;

public interface MonsterService {

    Monster getById(Long monsterId);

    List<Monster> findAll();

    void deleteById(Long monsterId);

    Monster save(Monster monster);
}
