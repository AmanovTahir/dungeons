package ru.otus.dungeons.exception;

public class UserGameNotFoundException extends RuntimeException {
    public UserGameNotFoundException(String message) {
        super(message);
    }
}
