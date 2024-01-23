package ru.otus.dungeons.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Item;
import ru.otus.dungeons.service.ItemService;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ItemHandler {

    private final ItemService service;

    public Item getRandomItem() {
        List<Item> items = service.getAll();
        int randomIndex = new Random().nextInt(items.size());
        return items.get(randomIndex);
    }

    public Item getHealthPotion() {
        return service.findHealthPotion();
    }
}
