package ru.otus.dungeons.mapper;

import org.springframework.stereotype.Component;
import ru.otus.dungeons.domain.CharacterEntity;
import ru.otus.dungeons.domain.UserCharacter;

@Component
public class CharacterMapper {

    public UserCharacter toSelected(CharacterEntity characterEntity) {
        return UserCharacter.builder()
                .agility(characterEntity.getAgility())
                .level(characterEntity.getLevel())
                .hitPower(characterEntity.getHitPower())
                .health(characterEntity.getHealth())
                .maxHealth(characterEntity.getMaxHealth())
                .type(characterEntity.getType())
                .shield(characterEntity.getShield())
                .name(characterEntity.getName())
                .build();
    }
}
