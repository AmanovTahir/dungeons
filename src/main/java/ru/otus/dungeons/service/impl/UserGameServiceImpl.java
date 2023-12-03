package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.UserGame;
import ru.otus.dungeons.exception.UserGameNotFoundException;
import ru.otus.dungeons.repository.UserGameRepository;
import ru.otus.dungeons.service.UserGameService;

@Service
@RequiredArgsConstructor
public class UserGameServiceImpl implements UserGameService {

    private final UserGameRepository userGameRepository;

    @Override
    public UserGame save(UserGame userGame) {
        return userGameRepository.save(userGame);
    }

    @Override
    public void update(UserGame userGame) {
        UserGame entity = findByUsername(userGame.getUsername());
        userGame.setId(entity.getId());
        userGameRepository.save(userGame);
    }

    @Override
    public UserGame findByUsername(String username) {
        return userGameRepository.findByUsername(username)
                .orElseThrow(() -> new UserGameNotFoundException("Game not found!"));
    }

    @Override
    public void deleteInfo(UserGame userGame) {
        UserGame entity = findByUsername(userGame.getUsername());
        entity.setDungeon(null);
        entity.setCharacter(null);
        entity.setDungeonLevel(0);
        userGameRepository.save(entity);
    }

    @Override
    public UserGame startNewGame(String username) {
        userGameRepository.findByUsername(username).ifPresent(userGameRepository::delete);
        return new UserGame(username);
    }
}
