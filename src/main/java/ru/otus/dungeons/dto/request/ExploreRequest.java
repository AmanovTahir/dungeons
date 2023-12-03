package ru.otus.dungeons.dto.request;


import lombok.Data;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.UserCharacter;

@Data
public class ExploreRequest {

    private String username;

    private UserCharacter character;

    private Dungeon dungeon;

    private int dungeonLevel;
}
