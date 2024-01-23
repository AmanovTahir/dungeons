# Dungeon - Online RPG Game

Welcome to Dungeon, an exciting single-player online RPG game where you embark on an epic journey through challenging dungeons, face fierce monsters, and build powerful characters. This README provides comprehensive information on the project structure, technologies used, and how to get started.

## Table of Contents

- [Game Overview](#game-overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Client-Side (Frontend)](#client-side-frontend)
- [Server-Side (Backend)](#server-side-backend)
- [Database](#database)
- [Keycloak Integration](#keycloak-integration)
- [Docker Compose](#docker-compose)
- [Getting Started](#getting-started)
  
## Game Overview

Dungeon is a single-player online RPG game with the following key features:

1. **Character Selection:** Players choose characters from four classes: Mage, Warrior, Rogue, and Priest. Each class has unique characteristics.

2. **Dungeon Entry:** Players enter dungeons, with each level offering a new environment and events.

3. **Random Events:** Throughout the game, players encounter various events, such as finding items or facing monsters. Randomness adds an element of surprise and variability to the game.

4. **Monster Battles:** When encountering a monster, a battle begins. Players and monsters exchange blows and attempt to dodge attacks. Health levels determine survival in battle.

5. **Level Up:** Successful victories over monsters lead to leveling up. Leveling up involves improving character attributes.

6. **Level Progression:** Players must navigate through all dungeon levels, each presenting new challenges and surprises. Completing all dungeon levels is the game's objective.

## Technologies Used

### Client-Side (Frontend)

- Thymeleaf and Fetch JS for dynamic HTML template creation and data exchange between the server and the web page.
- WebSocket for real-time game progress updates.

### Server-Side (Backend)

- Spring Core and Spring Security with JWT for user authentication and authorization.
- Spring MVC for handling web requests and managing the web interface.
- Spring Data JPA and Flyway for interacting with the database and data migrations.
- Spring WebSocket for real-time communication with the client.

### Database

- PostgreSQL for storing character and player progress data.

## Project Structure

The project follows a standard MVC (Model-View-Controller) architecture with separate packages for controllers, services, models, and repositories. The `resources` directory contains configuration files, including Keycloak and database settings.

## Keycloak Integration

Keycloak is integrated into the project for secure user identity management. Configuration details for Keycloak, such as client ID, authorization grant type, and issuer URI, can be found in the `application.yml` file.

## Docker Compose

The project includes a `docker-compose.yml` file for simplified deployment. Docker Compose configurations cover Keycloak and PostgreSQL databases.

## Getting Started

To get started with Dungeon:

1. Clone the repository.
2. Configure your PostgreSQL database and update database settings in `application.yml`.
3. Start Keycloak using Docker Compose:
