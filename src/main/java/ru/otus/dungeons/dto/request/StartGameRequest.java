package ru.otus.dungeons.dto.request;

import lombok.Data;
import ru.otus.dungeons.domain.CharacterEntity;
import ru.otus.dungeons.domain.Dungeon;

@Data
public class StartGameRequest {

    private CharacterEntity character;

    private Dungeon dungeon;
}
