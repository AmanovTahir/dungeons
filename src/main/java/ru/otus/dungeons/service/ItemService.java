package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAll();

    Item findHealthPotion();
}
