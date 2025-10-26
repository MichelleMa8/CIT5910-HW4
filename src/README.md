# Blackjack Solitaire

**Author:** Michelle Ma  
**Collaboration Statement:**  
I completed this project independently and did not work with a partner.

---

## Overview
This program implements a playable version of **Blackjack Solitaire**, a one-player card game inspired by Blackjack.  
The player draws cards from a shuffled deck and places them onto a 4×5 grid according to the game’s rules.  
Once all 16 scoring positions are filled (and up to 4 discards used), the game calculates and displays the final score.

---

## Files Included
- `Card.java` — Defines a `Card` class representing a single playing card, with methods for value calculation and formatted display.
- `Deck.java` — Defines a `Deck` class that stores 52 `Card` objects, supports shuffling, and deals cards.
- `BlackjackSolitaire.java` — Implements the game logic, including:
    - Board display and user interaction
    - Input validation and discard handling
    - Scoring of each row and column
    - Ace value adjustment and final score calculation
- `BlackjackSolitaireRunner.java` — Contains the `main` method to start and run one complete game.
