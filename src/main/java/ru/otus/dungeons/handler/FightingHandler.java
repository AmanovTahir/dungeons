package ru.otus.dungeons.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.domain.UserCharacter;
import ru.otus.dungeons.dto.request.AttackRequest;
import ru.otus.dungeons.dto.response.AttackResponse;
import ru.otus.dungeons.mapper.AttackResponseMapper;
import ru.otus.dungeons.service.impl.WebSocketService;

@Service
@RequiredArgsConstructor
@Slf4j
public class FightingHandler {
    private static final String MONSTER_ATTACK = "Монстер атакует!";

    private static final String HERO_ATTACK = "Герой атакует!";

    private final CharacterHandler characterHandler;

    private final MonsterHandler monsterHandler;

    private final WebSocketService webSocketService;

    private final AttackResponseMapper mapper;

    public AttackResponse fighting(AttackRequest request) {
        UserCharacter character = request.getCharacter();
        Monster monster = request.getMonster();
        request.setMonster(heroAttack(character, monster));
        request.setCharacter(monsterAttack(character, monster));
        return mapper.toResponse(request);
    }

    public boolean runaway() {
        if (Math.random() < 0.55) {
            webSocketService.send("Вы успешно убежали!");
            return true;
        } else {
            webSocketService.send("Убежать не удалось.");
            return false;
        }
    }

    private UserCharacter monsterAttack(UserCharacter character, Monster monster) {
        if (monsterHandler.isAlive(monster)) {
            log.info("Монстер {} атакует", monster.getName());
            webSocketService.send(MONSTER_ATTACK);
            return characterHandler.hit(character, monster.getHitPower());
        }
        return character;
    }

    private Monster heroAttack(UserCharacter character, Monster monster) {
        if (characterHandler.isAlive(character)) {
            log.info("Герой {} атакует", character.getName());
            webSocketService.send(HERO_ATTACK);
            return monsterHandler.hit(monster, character.getHitPower());
        }
        return monster;
    }
}
