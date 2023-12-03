package ru.otus.dungeons.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class DungeonPageController {

    @GetMapping
    public String startGame(Model model, @AuthenticationPrincipal OAuth2User principal) {
        addAttributeToModel(model, principal);
        return "start";
    }

    @GetMapping("game/select")
    public String selectPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        addAttributeToModel(model, principal);
        return "select";
    }

    @GetMapping("game/dungeon")
    public String dungeonPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        addAttributeToModel(model, principal);
        return "dungeon";
    }

    @GetMapping("monster/{monsterId}/attack")
    public String fightPage(Model model, @PathVariable Long monsterId, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("monsterId", monsterId);
        addAttributeToModel(model, principal);
        return "fight";
    }

    @GetMapping("/game/winner")
    public String winnerPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        addAttributeToModel(model, principal);
        return "winner";
    }

    @GetMapping("/game/game-over")
    public String gameOverPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        addAttributeToModel(model, principal);
        return "game-over";
    }

    @GetMapping("game/not-found")
    public String notFound(Model model, @AuthenticationPrincipal OAuth2User principal) {
        addAttributeToModel(model, principal);
        return "not-found-game";
    }

    private void addAttributeToModel(Model model, OAuth2User principal) {
        Object username = principal.getAttribute("preferred_username");
        model.addAttribute("username", username);
    }
}
