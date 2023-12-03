package ru.otus.dungeons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.service.DungeonService;

import java.util.List;

@RestController
@RequestMapping("/api/dungeon")
@RequiredArgsConstructor
public class DungeonController {

    private final DungeonService dungeonService;

    @GetMapping("/")
    public ResponseEntity<List<Dungeon>> listDungeons() {
        List<Dungeon> dungeons = dungeonService.getAll();
        return ResponseEntity.ok(dungeons);
    }

    @GetMapping("/{dungeonId}")
    public ResponseEntity<Dungeon> getDungeonInfo(@PathVariable Long dungeonId) {
        Dungeon dungeon = dungeonService.getDungeon(dungeonId);
        return ResponseEntity.ok(dungeon);
    }
}