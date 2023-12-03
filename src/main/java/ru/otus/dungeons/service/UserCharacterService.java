package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.UserCharacter;

public interface UserCharacterService {
    UserCharacter findById(Long id);

    UserCharacter save(UserCharacter character);

    void remove(UserCharacter character);
}
