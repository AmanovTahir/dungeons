package ru.otus.dungeons.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.domain.UserCharacter;

@Data
@AllArgsConstructor
public class AttackResponse {
    private UserCharacter character;

    private Monster monster;
}
