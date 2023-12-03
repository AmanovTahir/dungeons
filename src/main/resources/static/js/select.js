document.addEventListener("DOMContentLoaded", () => {
    const characterTable = document.getElementById("characterTable");
    const dungeonTable = document.getElementById("dungeonTable");
    const sendToDungeonButton = document.getElementById("sendToDungeonButton");

    const state = {
        selectedCharacter: null,
        selectedDungeon: null,
    };

    async function fetchCharacters() {
        try {
            const response = await fetch('/api/characters/');
            const characters = await response.json();
            displayCharacters(characters);
        } catch (error) {
            console.error("Error:", error);
        }
    }

    async function fetchDungeons() {
        try {
            const response = await fetch('/api/dungeon/');
            const dungeons = await response.json();
            displayDungeons(dungeons);
        } catch (error) {
            console.error("Error:", error);
        }
    }

    function displayCharacters(characters) {
        const characterListBody = document.getElementById("characterListBody");
        characterListBody.innerHTML = "";
        characters.forEach(character => {
            const row = createTableRow(character.id, character.name);
            row.addEventListener("click", () => selectCharacter(character, row));
            characterListBody.appendChild(row);
        });
    }

    function displayDungeons(dungeons) {
        const dungeonListBody = document.getElementById("dungeonListBody");
        dungeonListBody.innerHTML = "";
        dungeons.forEach(dungeon => {
            const row = createTableRow(dungeon.id, dungeon.levels);
            row.addEventListener("click", () => selectDungeon(dungeon, row));
            dungeonListBody.appendChild(row);
        });
    }

    function createTableRow(id, levels) {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${levels}</td>
        `;
        return row;
    }

    function selectCharacter(character, row) {
        state.selectedCharacter = character;
        deselectAllRows(characterTable);
        row.classList.add("selected");
        enableSendButton();
    }

    function selectDungeon(dungeon, row) {
        state.selectedDungeon = dungeon;
        deselectAllRows(dungeonTable);
        row.classList.add("selected");
        enableSendButton();
    }

    function deselectAllRows(table) {
        const rows = table.querySelectorAll("tbody tr");
        rows.forEach(row => row.classList.remove("selected"));
    }

    function enableSendButton() {
        sendToDungeonButton.removeAttribute("disabled");
    }

    async function sendToDungeon() {
        if (state.selectedCharacter && state.selectedDungeon) {
            const dto = {
                character: state.selectedCharacter,
                dungeon: state.selectedDungeon,
            };
            try {
                const response = await fetch('/api/game/start', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(dto),
                });
                if (response.ok) {
                    location.href = '/game/dungeon';
                } else {
                    console.error("Ошибка при отправке данных.");
                }
            } catch (error) {
                console.error("Ошибка:", error);
            }
        } else {
            console.error("Пожалуйста, выберите персонажа и подземелье перед отправкой.");
        }
    }

    sendToDungeonButton.addEventListener("click", sendToDungeon);

    fetchCharacters();
    fetchDungeons();
});
