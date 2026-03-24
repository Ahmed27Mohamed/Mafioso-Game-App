# Mafioso Game 🔍🕵️‍♂️

A social deduction party game built using **Kotlin** and **Jetpack Compose**, where players must uncover the hidden Mafioso among them through clues, discussion, and strategic thinking.

Designed to be played on a **single device**, the game creates an engaging and suspenseful experience with dynamically generated crimes, roles, and clues.

---

## 🎮 Overview

Mafioso is a multiplayer party game where one player is secretly assigned the role of the **Mafioso**, while others play different roles connected to the crime.

Players must analyze clues, question each other, and vote to identify the Mafioso before it's too late.

At the end of the game, a full **crime story** is revealed explaining what happened and who committed the crime.

---

## 🧠 Game Concept

- 👤 One hidden Mafioso
- 👥 Other players have roles related to the crime
- 🕵️ Clues are generated dynamically
- 🗳️ Players discuss and vote each round
- 🎭 Some clues may mislead players intentionally
- 📖 Final story reveals the truth

---

## 🚀 Features

- 🎭 Random role assignment for each player
- 🧩 Dynamic clue generation system
- 🕵️ Suspicion-based gameplay
- 🗳️ Voting system after each round
- ⏱️ Discussion timer (e.g., 2 minutes per round)
- 📱 Single-device multiplayer (pass-and-play)
- 🔊 Sound effects (optional)
- 📖 Final crime story generation
- 🎬 Animated reveal of results
- 🔁 High replayability with varied scenarios

---

## 🎯 Gameplay Flow

1. Players enter their names
2. Roles are assigned secretly
3. Each player views their role privately (device passing)
4. Clues are presented
5. Players discuss within a time limit
6. Voting begins
7. A player is eliminated
8. Repeat until:
   - Mafioso is caught ✅
   - Mafioso outnumbers others ❌

---

## 🧩 Roles System

- 🟥 Mafioso (1 player)
- 🟦 Other roles related to the crime (e.g. doctor assistant, security, patient, etc.)

Each role is connected to the story, making the game more immersive and realistic.

---

## 🕵️ Clue System

The game generates clues that:
- Point toward the Mafioso
- Sometimes implicate innocent players
- Require critical thinking
- Increase game difficulty

---

## 📖 Story System

At the end of the game:

- A full crime story is revealed
- Includes:
  - Type of crime
  - Location
  - Mafioso role
  - How the crime was committed

This creates a cinematic ending to each session 🎬

---

## 🏗️ Built With

- **Kotlin**
- **Jetpack Compose**
- **Android SDK**

---

## 🧠 Architecture

- MVVM Architecture
- Clean separation of concerns
- State management using Compose
- Modular game logic
- Reusable UI components

---

## 📱 Screenshots

### 🎮 Game Flow Preview

| Setup | Players | Role Reveal |
|-------|--------|-------------|
| ![1](https://i.postimg.cc/rsvQj3BN/1.jpg) | ![2](https://i.postimg.cc/Rh3gc4Dt/2.jpg) | ![3](https://i.postimg.cc/WzD5MTHq/3.jpg) |

| Clues | Discussion | Voting |
|-------|------------|--------|
| ![4](https://i.postimg.cc/W3Y9FhXT/4.jpg) | ![5](https://i.postimg.cc/Y9DXGh8y/5.jpg) | ![6](https://i.postimg.cc/sXHnQvK9/6.jpg) |

| Elimination | Suspicion | Game Progress |
|-------------|------------|---------------|
| ![7](https://i.postimg.cc/MTpPpy0D/7.jpg) | ![8](https://i.postimg.cc/85zKzhdM/8.jpg) | ![9](https://i.postimg.cc/G2m7mkP4/9.jpg) |

### 🎬 Final Story & Result

| Ending | Story |
|--------|--------|
| ![10](https://i.postimg.cc/ZR575rP6/10.jpg) | ![11](https://i.postimg.cc/g0DtpMhn/11.jpg) |

---

## ⚙️ Installation

git clone https://github.com/your-username/mafioso-game.git
cd mafioso-game

---

## 🔥 Key Highlights
Complex game logic implementation
Dynamic content generation
Real-world party game concept
Strong UX interaction (pass device gameplay)
High replay value

---

## 🧪 Technical Challenges
Generating balanced clues (not too obvious, not too vague)
Managing hidden information between players
Designing replayable random scenarios
Handling game state transitions smoothly
Creating immersive storytelling logic

---

## 📈 Future Improvements
Bluetooth / WiFi multiplayer 🔗
Online mode 🌐
AI-driven dynamic storytelling 🤖
More roles and scenarios 🎭
Difficulty levels ⚙️
Custom game settings 🎮
