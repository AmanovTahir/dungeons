package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.UserGame;

public interface UserGameService {

    UserGame save(UserGame userGame);

    void update(UserGame userGame);

    UserGame findByUsername(String username);

    void deleteInfo(UserGame userGame);

    UserGame startNewGame(String username);
}
