package ru.otus.dungeons.exception;

public class MonsterNotFoundException extends RuntimeException {
    public MonsterNotFoundException(String message) {
        super(message);
    }
}
