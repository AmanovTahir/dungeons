-- Создание таблицы characters по умолчанию
CREATE TABLE characters
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    level      INT DEFAULT 1,
    health     INT          NOT NULL,
    max_health INT          NOT NULL,
    hit_power  INT          NOT NULL,
    shield     INT          NOT NULL,
    agility    DOUBLE PRECISION,
    type       VARCHAR(255) NOT NULL
);

-- Создание таблицы user_characters
CREATE TABLE user_characters
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(255)  NOT NULL,
    level          INT DEFAULT 1 NOT NULL,
    health_potions int DEFAULT 3 NOT NULL,
    health         INT           NOT NULL,
    max_health     INT           NOT NULL,
    hit_power      INT           NOT NULL,
    shield         INT           NOT NULL,
    agility        DOUBLE PRECISION,
    type           VARCHAR(255)  NOT NULL
);

-- Создание таблицы monsters
CREATE TABLE monsters
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    level      INT          NOT NULL,
    health     INT          NOT NULL,
    max_health INT          NOT NULL,
    hit_power  INT          NOT NULL
);

-- Создание таблицы dungeons
CREATE TABLE dungeons
(
    id     SERIAL PRIMARY KEY,
    levels INT NOT NULL
);


-- Создание таблицы users
CREATE TABLE users_game
(
    id                SERIAL PRIMARY KEY,
    username          VARCHAR(255) NOT NULL UNIQUE,
    user_character_id BIGINT,
    dungeon_id        BIGINT,
    dungeon_level     BIGINT,
    FOREIGN KEY (user_character_id) REFERENCES user_characters (id),
    FOREIGN KEY (dungeon_id) REFERENCES dungeons (id)
);

-- Создание таблицы items
CREATE TABLE items
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    character_id BIGINT,
    type         VARCHAR(255) NOT NULL,
    buff         BIGINT,
    description  TEXT
);

