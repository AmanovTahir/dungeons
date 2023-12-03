package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.UserCharacter;
import ru.otus.dungeons.exception.CharacterNotFoundException;
import ru.otus.dungeons.repository.UserCharacterRepository;
import ru.otus.dungeons.service.UserCharacterService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCharacterServiceImpl implements UserCharacterService {

    private final UserCharacterRepository selectedRepository;

    @Override
    public UserCharacter findById(Long id) {
        return selectedRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException("Character not found!"));
    }

    @Override
    public UserCharacter save(UserCharacter character) {
        return selectedRepository.save(character);
    }

    @Override
    public void remove(UserCharacter character) {
        selectedRepository.deleteById(character.getId());
    }
}
