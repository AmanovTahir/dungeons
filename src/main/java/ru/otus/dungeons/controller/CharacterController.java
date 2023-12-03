package ru.otus.dungeons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dungeons.domain.UserCharacter;
import ru.otus.dungeons.handler.CharacterHandler;

@RestController
@RequestMapping("/api/game/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterHandler characterHandler;

    @PostMapping("/level-up")
    public ResponseEntity<Void> levelUpCharacter(@RequestBody UserCharacter character) {
        characterHandler.levelUp(character);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/equip")
    public ResponseEntity<Void> equipCharacter(@RequestBody UserCharacter character) {
        characterHandler.addInventory(character);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/heal")
    public ResponseEntity<UserCharacter> healCharacter(@RequestBody UserCharacter character) {
        return ResponseEntity.ok(characterHandler.heal(character));
    }

    @PutMapping("/alive")
    public ResponseEntity<Boolean> isAlive(@RequestBody UserCharacter character) {
        return ResponseEntity.ok(characterHandler.isAlive(character));
    }
}
