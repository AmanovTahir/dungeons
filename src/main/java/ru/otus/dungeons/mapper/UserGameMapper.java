package ru.otus.dungeons.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dungeons.domain.UserGame;
import ru.otus.dungeons.dto.request.ExploreRequest;
import ru.otus.dungeons.dto.response.ExploreResponse;
import ru.otus.dungeons.dto.response.UserGameResponse;

@Component
@AllArgsConstructor
public class UserGameMapper {

    public UserGame toUserGame(ExploreResponse response, ExploreRequest dto) {
        return UserGame.builder()
                .dungeonLevel(response.getDungeonLevel())
                .username(dto.getUsername())
                .dungeon(dto.getDungeon())
                .character(dto.getCharacter())
                .build();
    }

    public UserGameResponse toUserGameResponse(UserGame userGame) {
        return UserGameResponse.builder()
                .dungeonLevel(userGame.getDungeonLevel())
                .dungeon(userGame.getDungeon())
                .character(userGame.getCharacter())
                .username(userGame.getUsername())
                .build();
    }
}
