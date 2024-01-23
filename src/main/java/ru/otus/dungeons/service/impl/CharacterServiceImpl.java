package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.CharacterEntity;
import ru.otus.dungeons.repository.CharacterRepository;
import ru.otus.dungeons.service.CharacterService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public List<CharacterEntity> findAll() {
        return characterRepository.findAll();
    }
}
