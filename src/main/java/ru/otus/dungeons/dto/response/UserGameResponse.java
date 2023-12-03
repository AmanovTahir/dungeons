package ru.otus.dungeons.dto.response;

import lombok.Builder;
import lombok.Data;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.UserCharacter;

@Data
@Builder
public class UserGameResponse {
    private String username;

    private UserCharacter character;

    private Dungeon dungeon;

    private int dungeonLevel;
}
