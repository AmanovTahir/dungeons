document.addEventListener("DOMContentLoaded", () => {
    const characterNameElement = document.getElementById("characterName");
    const characterLevelElement = document.getElementById("characterLevel");
    const characterHealthElement = document.getElementById("characterHealth");
    const characterHitPowerElement = document.getElementById("characterHitPower");
    const characterShieldElement = document.getElementById("characterShield");
    const characterAgilityElement = document.getElementById("characterAgility");
    const healthPotionImagesContainer = document.getElementById('healthPotionImages');
    const characterItemsElement = document.getElementById("characterItems");

    const monsterNameElement = document.getElementById("monsterName");
    const monsterLevelElement = document.getElementById("monsterLevel");
    const monsterHealthElement = document.getElementById("monsterHealth");
    const monsterHitPowerElement = document.getElementById("monsterHitPower");

    const attackButton = document.getElementById("attackButton");
    const useHealthPotionButton = document.getElementById("useHealthPotionButton");
    const runawayButton = document.getElementById("runawayButton");

    let request = null;
    let characterData = null;
    let dungeonData = null;
    let monsterData = null;

    if (!request) {
        updateCharacterAndMonsterData();
    }

    function killMonsterAndUpLevel(monsterData, characterData) {
        fetch('/api/monster/' + monsterData.id, {
            method: 'DELETE',
        }).then(response => {
            if (response.ok) {
                fetch('/api/game/character/level-up', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(characterData),
                }).then(response => {
                    if (response.ok) {
                        location.href = '/game/dungeon';
                    }
                })
            }
        })

        Promise.all([deleteMonsterRequest, levelUpRequest])
            .then(response => {
                if (response.ok) {
                    window.location.href = '/game/dungeon';
                }
            })
    }

    function gameOverRequest(request) {
        fetch('/api/game/game-over', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        }).then(response => {
            if (response.ok) {
                window.location.href = '/game/game-over';
            } else {
                console.error("Ошибка при удалении данных у пользователя:", error);
            }
        })
    }

    function updateCharacterData(character) {
        characterData = character;
        characterNameElement.textContent = character.name;
        characterLevelElement.textContent = character.level;
        characterHealthElement.textContent = character.health + "/" + character.maxHealth;
        characterHitPowerElement.textContent = character.hitPower;
        characterShieldElement.textContent = character.shield;
        characterAgilityElement.textContent = character.agility;
        let healthPotionsCount = parseInt(character.healthPotions);
        healthPotionImagesContainer.innerHTML = '';
        for (let i = 0; i < healthPotionsCount; i++) {
            const img = document.createElement('img');
            img.src = '/healing_potion.png';
            img.width = 20;
            img.height = 20;
            healthPotionImagesContainer.appendChild(img);
        }
        if (character.inventory.length === 0) {
            characterItemsElement.textContent = "В ваших карманцах пусто, Сир!";
        } else {
            characterItemsElement.textContent = character.inventory.map(item => item.name).join(', ');
        }
    }

    function updateMonsterData(monster) {
        monsterNameElement.textContent = monster.name;
        monsterLevelElement.textContent = monster.level;
        monsterHealthElement.textContent = monster.health;
        monsterHitPowerElement.textContent = monster.hitPower;
    }

    function updateCharacterAndMonsterData() {
        fetch(`/api/game/user/${username}`)
            .then(response => response.json())
            .then(user => {
                request = user;
                characterData = user.character;
                dungeonData = user.dungeon;
                updateCharacterData(characterData);
                fetch(`/api/monster/` + monsterId)
                    .then(response => response.json())
                    .then(monster => {
                        monsterData = monster;
                        updateMonsterData(monster);
                    })
                    .catch(error => {
                        console.error("Ошибка при запросе данных о монстре:", error);
                    });
            })
            .catch(error => {
                console.error("Ошибка при запросе данных о персонаже:", error);
            });
    }

    function checkGameOver() {
        fetch('/api/game/character/alive', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(characterData),
        }).then(response => response.json())
            .then(data => {
                if (!data) {
                    gameOverRequest(request);
                }
            })
    }

    function checkKillMonster(characterData) {
        fetch('/api/monster/alive', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(monsterData),
        }).then(response => response.json())
            .then(data => {
                if (!data) {
                    killMonsterAndUpLevel(monsterData, characterData);
                }
            })
    }

    function attack(dto) {
        fetch('/api/game/attack', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dto),
        })
            .then(response => response.json())
            .then(data => {
                characterData = data.character;
                monsterData = data.monster;
                checkGameOver();
                checkKillMonster(characterData);
                updateMonsterData(monsterData)
                updateCharacterData(characterData)
            });
    }

    attackButton.addEventListener("click", () => {
        const dto = {
            character: characterData,
            dungeon: dungeonData,
            monster: monsterData
        };
        attack(dto);
    });

    useHealthPotionButton.addEventListener("click", () => {
        if (characterData.healthPotions > 0) {
            fetch('/api/game/character/heal', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(characterData),
            })
                .then(response => response.json())
                .then(character => {
                    updateCharacterData(character)
                    console.log("Вы успешно вылечились!");
                })
                .catch(error => {
                    console.error("Ошибка при выполнении запроса:", error);
                });
        } else {
            useHealthPotionButton.disabled = true;
        }
    });

    runawayButton.addEventListener("click", () => {
        fetch('/api/game/runaway', {
            method: 'GET'
        })
            .then(response => response.json())
            .then(result => {
                if (result) {
                    console.log("Вы успешно убежали!");
                    location.href = '/game/dungeon';
                } else {
                    console.log("Убежать не удалось.");
                    runawayButton.disabled = true;
                }
            })
            .catch(error => {
                console.error("Ошибка при выполнении запроса:", error);
            });
    });
});
