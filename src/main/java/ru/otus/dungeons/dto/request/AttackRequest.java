package ru.otus.dungeons.dto.request;

import lombok.Data;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.domain.UserCharacter;

@Data
public class AttackRequest {
    private UserCharacter character;

    private Monster monster;

    private Dungeon dungeon;
}
