package ru.otus.dungeons.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Item;
import ru.otus.dungeons.domain.UserCharacter;
import ru.otus.dungeons.service.UserCharacterService;
import ru.otus.dungeons.service.impl.WebSocketService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterHandler {
    private static final int LEVEL_UP = 1;

    private static final int DAMAGE_UP = 10;

    private static final double AGILITY_UP = 0.07;

    private final UserCharacterService characterService;

    private final WebSocketService webSocketService;

    private final ItemHandler itemHandler;

    public void levelUp(UserCharacter character) {
        updateCharacter(character);
        webSocketService.send("Герой: (" + character.getName() + "), повысел уровень: " + character.getLevel());
        log.info("Герой {} повысел уровень: {}", character.getName(), character.getLevel());
    }

    public UserCharacter hit(UserCharacter character, int damage) {
        if (Math.random() < 0.15 + character.getAgility()) {
            int result = damage - character.getShield();
            character.setHealth(character.getHealth() - result);
            log.info("Герой {} получил урон: {}, осталось жизней: {}",
                    character.getName(), damage, character.getHealth());
            webSocketService.send(String.format("Вы получили урон: (%d), осталось жизней: %d",
                    damage, character.getHealth()));
            return characterService.save(character);
        } else {
            log.info("Герой {} уклонился от удара", character.getName());
            webSocketService.send("Вы уклонились от удара");
        }
        return character;
    }

    public boolean isAlive(UserCharacter character) {
        return character.getHealth() > 0;
    }

    public void addInventory(UserCharacter character) {
        equipCharacter(character);
        characterService.save(character);
    }

    private void equipCharacter(UserCharacter character) {
        Item randomItem = itemHandler.getRandomItem();
        if (character.getInventory().contains(randomItem)) {
            return;
        }
        character.getInventory().add(randomItem);
        executeItemBuff(character, randomItem);
    }

    private void executeItemBuff(UserCharacter character, Item item) {
        switch (item.getType()) {
            case WEAPON -> character.setHitPower(character.getHitPower() + item.getBuff());
            case SHIELD -> character.setShield(character.getShield() + item.getBuff());
            case HEALTH -> character.setHealth(character.getHealth() + item.getBuff());
            case HEALTH_POTION -> character.setHealthPotions(character.getHealthPotions() + 1);
        }
    }

    private void updateCharacter(UserCharacter character) {
        character.setLevel(character.getLevel() + LEVEL_UP);
        character.setHitPower(character.getHitPower() + DAMAGE_UP);
        character.setAgility(calculateAgility(character.getAgility()));
        character.setHealth(character.getHealth() + 20);
        character.setMaxHealth(character.getMaxHealth() + 50);
        equipCharacter(character);
        characterService.save(character);
    }

    private double calculateAgility(double agility) {
        double result = agility + AGILITY_UP;
        return Math.round(result * 100.0) / 100.0;
    }

    public UserCharacter heal(UserCharacter character) {
        Item healthPotion = itemHandler.getHealthPotion();
        if (character.getHealth() == character.getMaxHealth()) {
            return character;
        } else if (healthPotion.getBuff() + character.getHealth() >= character.getMaxHealth()) {
            character.setHealth(character.getMaxHealth());
            character.setHealthPotions(character.getHealthPotions() - 1);
        } else {
            character.setHealthPotions(character.getHealthPotions() - 1);
            character.setHealth(character.getHealth() + healthPotion.getBuff());
        }
        webSocketService.send("Ваш персонаж вылечился на: " + healthPotion.getBuff() + ", осталось жизней: "
                + character.getHealth());
        return characterService.save(character);
    }
}
