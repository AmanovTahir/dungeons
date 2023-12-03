package ru.otus.dungeons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dungeons.dto.response.UserGameResponse;
import ru.otus.dungeons.mapper.UserGameMapper;
import ru.otus.dungeons.service.UserGameService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/user")
public class UserGameController {
    private final UserGameService service;

    private final UserGameMapper mapper;

    @GetMapping("/{username}")
    public ResponseEntity<UserGameResponse> getUserGame(@PathVariable String username) {
        return ResponseEntity.ok(mapper.toUserGameResponse(service.findByUsername(username)));
    }
}
