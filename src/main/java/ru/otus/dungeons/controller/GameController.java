package ru.otus.dungeons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dungeons.domain.UserGame;
import ru.otus.dungeons.dto.request.AttackRequest;
import ru.otus.dungeons.dto.request.ExploreRequest;
import ru.otus.dungeons.dto.request.StartGameRequest;
import ru.otus.dungeons.dto.response.AttackResponse;
import ru.otus.dungeons.dto.response.ExploreResponse;
import ru.otus.dungeons.handler.DungeonExploreHandler;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final DungeonExploreHandler handler;

    @PostMapping("/start")
    public ResponseEntity<Void> start(@RequestBody StartGameRequest request,
                                      @AuthenticationPrincipal OAuth2User principal) {
        String username = principal.getAttribute("preferred_username");
        handler.start(request, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/explore")
    public ResponseEntity<ExploreResponse> explore(@RequestBody ExploreRequest request) {
        return ResponseEntity.ok(handler.explore(request));
    }

    @PostMapping("/attack")
    public ResponseEntity<AttackResponse> attack(@RequestBody AttackRequest request) {
        return ResponseEntity.ok(handler.attack(request));
    }

    @GetMapping("/runaway")
    public ResponseEntity<Boolean> runaway() {
        return ResponseEntity.ok(handler.runaway());
    }

    @PostMapping("/game-over")
    public ResponseEntity<Void> runaway(@RequestBody UserGame request) {
        handler.gameOver(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/winner")
    public ResponseEntity<Void> winner(@RequestBody UserGame request) {
        handler.winner(request);
        return ResponseEntity.ok().build();
    }
}
