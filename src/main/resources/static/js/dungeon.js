const dungeonLevelsElement = document.getElementById("dungeonLevels");
const characterNameElement = document.getElementById("characterName");
const characterLevelElement = document.getElementById("characterLevel");
const characterHealthElement = document.getElementById("characterHealth");
const characterHitPowerElement = document.getElementById("characterHitPower");
const characterShieldElement = document.getElementById("characterShield");
const characterAgilityElement = document.getElementById("characterAgility");
const characterItemsElement = document.getElementById("characterItems");
const healthPotionImagesContainer = document.getElementById('healthPotionImages');

const nextLevelButton = document.getElementById("nextLevelButton");

let request = null;

fetch(`/api/game/user/${username}`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`User not found: ${response.statusText}`);
        }
        return response.json();
    })
    .then(user => {
        request = user;
        updateCharacterData(user);
    })
    .catch(error => {
        location.href = '/game/not-found';
    });


function updateCharacterData(user) {
    characterNameElement.textContent = user.character.name;
    characterLevelElement.textContent = user.character.level;
    characterHealthElement.textContent = user.character.health + "/" + user.character.maxHealth;
    characterHitPowerElement.textContent = user.character.hitPower;
    characterShieldElement.textContent = user.character.shield;
    characterAgilityElement.textContent = user.character.agility;
    let healthPotionsCount = parseInt(user.character.healthPotions);
    for (let i = 0; i < healthPotionsCount; i++) {
        const img = document.createElement('img');
        img.src = '/healing_potion.png';
        img.width = 20;
        img.height = 20;
        healthPotionImagesContainer.appendChild(img);
    }
    if (user.character.inventory.length === 0) {
        characterItemsElement.textContent = "В ваших карманцах пусто, Сир!";
    } else {
        characterItemsElement.textContent = user.character.inventory.map(item => item.name).join(', ');
    }
    dungeonLevelsElement.textContent = user.dungeonLevel + "/" + user.dungeon.levels;
}

nextLevelButton.addEventListener("click", () => {
    function winnerFetch() {
        fetch('/api/game/winner', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        }).then(response => {
            if (response.ok) {
                window.location.href = '/game/winner';
            } else {
                console.error("Ошибка при удалении данных у пользователя:", error);
            }
        })
    }

    function monsterFetch() {
        fetch(`/api/monster`)
            .then(response => response.json())
            .then(monster => {
                const monsterId = monster.id;
                console.log("На вас напали!");
                location.href = `/monster/` + monsterId + `/attack`;
            })
            .catch(error => {
                console.error("Ошибка при запросе данных о монстре:", error);
            })
    }

    function itemFetch() {
        console.log("Вы нашли предмет!");
        fetch('/api/game/character/equip', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user.character),
        }).then(r => {
            location.href = '/game/dungeon';
        });
    }

    if (request) {
        console.log("Данные о пользователе:", request);
        fetch("/api/game/explore", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(request)
        })
            .then(response => response.json())
            .then(data => {
                if (data.dungeonEnd) {
                    winnerFetch();
                } else if (data.attack) {
                    monsterFetch();
                } else if (data.hasItem) {
                    itemFetch();
                } else {
                    console.log("Вы спустились на уровень ниже.");
                    location.href = '/game/dungeon';
                }
            })
            .catch(error => {
                console.error("Ошибка при выполнении запроса:", error);
            });
    }
});
