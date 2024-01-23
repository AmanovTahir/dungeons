package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.CharacterEntity;

import java.util.List;

public interface CharacterService {

    List<CharacterEntity> findAll();
}
