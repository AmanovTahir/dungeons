package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Item;
import ru.otus.dungeons.exception.ItemNotFoundException;
import ru.otus.dungeons.repository.ItemRepository;
import ru.otus.dungeons.service.ItemService;

import java.util.List;

import static ru.otus.dungeons.domain.enumeration.ItemType.HEALTH_POTION;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new ItemNotFoundException("No items found");
        }
        return items;
    }

    @Override
    public Item findHealthPotion() {
        return itemRepository.findByTypeIs(HEALTH_POTION).orElseThrow(() -> new ItemNotFoundException("Not found"));
    }
}
