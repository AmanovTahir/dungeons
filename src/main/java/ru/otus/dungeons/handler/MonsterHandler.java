package ru.otus.dungeons.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.exception.MonsterNotFoundException;
import ru.otus.dungeons.service.MonsterService;
import ru.otus.dungeons.service.impl.WebSocketService;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonsterHandler {

    private final MonsterService service;

    private final WebSocketService webSocketService;


    public Monster getRandom() {
        List<Monster> allMonsters = service.findAll();
        if (allMonsters.isEmpty()) {
            throw new MonsterNotFoundException("No monsters found");
        }

        int randomIndex = new Random().nextInt(allMonsters.size());
        Monster monster = allMonsters.get(randomIndex);
        webSocketService.send("На героя напал: " + monster.getName());
        log.info("На героя напал: " + monster.getName());
        return monster;
    }


    public void killMonster(Long monsterId) {
        Monster monster = service.getById(monsterId);
        monster.setHealth(monster.getMaxHealth());
        log.info("Монстер {} умер", monster.getName());
        webSocketService.send(monster.getName() + " повержен");
        service.save(monster);
    }


    public Monster hit(Monster monster, int damage) {
        if (Math.random() > 0.15) {
            int health = monster.getHealth() - damage;
            monster.setHealth(health);
            log.info("Монстер {} получил урон: {}, осталось жизней: {}",
                    monster.getName(), damage, monster.getHealth());
            webSocketService.send(String.format("%s получил урон: (%d), осталось жизней: %d",
                    monster.getName(), damage, monster.getHealth()));
        } else {
            log.info("Монстер {} уклонился от удара", monster.getName());
            webSocketService.send(monster.getName() + " уклонился от удара");
        }
        return monster;
    }


    public boolean isAlive(Monster monster) {
        return monster.getHealth() > 0;
    }

    public Monster getById(Long monsterId) {
        return service.getById(monsterId);
    }
}
