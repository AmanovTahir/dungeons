package ru.otus.dungeons.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.dungeons.exception.UserGameNotFoundException;

@ControllerAdvice
public class GameExceptionHandler {
    @ExceptionHandler(UserGameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<String> handleUserGameNotFoundException(UserGameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found!");
    }
}
