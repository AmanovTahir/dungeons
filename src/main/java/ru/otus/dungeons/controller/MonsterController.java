package ru.otus.dungeons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.handler.MonsterHandler;

@RestController
@RequestMapping("/api/monster")
@RequiredArgsConstructor
public class MonsterController {
    private final MonsterHandler monsterHandler;

    @GetMapping
    public ResponseEntity<Monster> getRandomMonster() {
        return ResponseEntity.ok(monsterHandler.getRandom());
    }

    @GetMapping("/{monsterId}")
    public ResponseEntity<Monster> getMonsterById(@PathVariable Long monsterId) {
        return ResponseEntity.ok(monsterHandler.getById(monsterId));
    }

    @DeleteMapping("/{monsterId}")
    public ResponseEntity<Void> removeMonsterById(@PathVariable Long monsterId) {
        monsterHandler.killMonster(monsterId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alive")
    public ResponseEntity<Boolean> isAlive(@RequestBody Monster monster) {
        return ResponseEntity.ok(monsterHandler.isAlive(monster));
    }
}
