package ru.otus.dungeons.dto.response;

import lombok.Data;

@Data
public class ExploreResponse {
    private int dungeonLevel;

    private boolean isAttack;

    private boolean isHasItem;

    private boolean isDungeonEnd;
}
