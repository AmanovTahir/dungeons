package ru.otus.dungeons.mapper;

import org.springframework.stereotype.Component;
import ru.otus.dungeons.dto.request.AttackRequest;
import ru.otus.dungeons.dto.response.AttackResponse;

@Component
public class AttackResponseMapper {

    public AttackResponse toResponse(AttackRequest request) {
        return new AttackResponse(request.getCharacter(), request.getMonster());
    }
}
