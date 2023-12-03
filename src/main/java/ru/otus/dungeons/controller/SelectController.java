package ru.otus.dungeons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dungeons.domain.CharacterEntity;
import ru.otus.dungeons.service.CharacterService;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class SelectController {
    private final CharacterService service;

    @GetMapping("/")
    public ResponseEntity<List<CharacterEntity>> listCharacters() {
        return ResponseEntity.ok(service.findAll());
    }
}
