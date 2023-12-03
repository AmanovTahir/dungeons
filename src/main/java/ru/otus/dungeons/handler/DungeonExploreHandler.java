package ru.otus.dungeons.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.UserCharacter;
import ru.otus.dungeons.domain.UserGame;
import ru.otus.dungeons.dto.request.AttackRequest;
import ru.otus.dungeons.dto.request.ExploreRequest;
import ru.otus.dungeons.dto.request.StartGameRequest;
import ru.otus.dungeons.dto.response.AttackResponse;
import ru.otus.dungeons.dto.response.ExploreResponse;
import ru.otus.dungeons.mapper.CharacterMapper;
import ru.otus.dungeons.mapper.UserGameMapper;
import ru.otus.dungeons.service.UserCharacterService;
import ru.otus.dungeons.service.UserGameService;
import ru.otus.dungeons.service.impl.WebSocketService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DungeonExploreHandler {
    private static final String NEXT_LEVEL_MESSAGE = "Продвигаемся на следующий уровень!";

    private final UserCharacterService characterService;

    private final FightingHandler fightingHandler;

    private final UserGameService userGameService;

    private final WebSocketService webSocketService;

    private final UserGameMapper mapper;

    private final CharacterMapper characterMapper;

    public void start(StartGameRequest request, String username) {
        UserCharacter character = characterService.save(characterMapper.toSelected(request.getCharacter()));
        UserGame userGame = userGameService.startNewGame(username);
        userGame.setDungeon(request.getDungeon());
        userGame.setCharacter(character);
        userGameService.save(userGame);
    }

    public ExploreResponse explore(ExploreRequest dto) {
        webSocketService.send(NEXT_LEVEL_MESSAGE);
        var response = new ExploreResponse();
        if (dto.getDungeonLevel() != dto.getDungeon().getLevels()) {
            getGameEvent(response);
        } else {
            response.setDungeonEnd(true);
        }
        response.setDungeonLevel(dto.getDungeonLevel() + 1);
        userGameService.update(mapper.toUserGame(response, dto));
        return response;
    }

    public AttackResponse attack(AttackRequest request) {
        return fightingHandler.fighting(request);
    }

    public boolean runaway() {
        return fightingHandler.runaway();
    }

    public void gameOver(UserGame request) {
        userGameService.deleteInfo(request);
    }

    public void winner(UserGame request) {
        request.setDungeonLevel(0);
        userGameService.update(request);
    }

    private void getGameEvent(ExploreResponse response) {
        if (Math.random() < 0.75) {
            response.setAttack(true);
        } else if (Math.random() < 0.15) {
            response.setHasItem(true);
        }
    }
}
