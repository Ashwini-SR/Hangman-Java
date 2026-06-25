#  Hangman Game — Core Java Terminal

> Built using Core Java only — no JavaFX, no Maven, no external libraries

##  About
A fully functional terminal-based Hangman game built from scratch 
using pure Java Standard Library. Guess the secret word before 
the man is fully hanged — you get 6 lives!

##  Featuresgit add README.md
- 50 words across categories (fruits, animals, music, tech, space and more)
- Hints given for every word
- 6 lives with step-by-step ASCII gallows art
- Colour-coded alphabet keyboard (green = correct, red = wrong)
- Live timer counting up every second
- Leaderboard saved to CSV file (persistent across sessions)
- Clean MVC architecture separating logic, view and control

##  File Structure
| File | Role |
|---|---|
| WordBank.java | 50 hardcoded words + hints in parallel String arrays |
| HangmanArt.java | 7 ASCII gallows stages in a 2D String array |
| GameEngine.java | All game logic — HashSet, LinkedList, char array |
| ScoreEntry.java | Comparable value object for leaderboard entries |
| Leaderboard.java | CSV file persistence with sorted ArrayList |
| Renderer.java | Terminal UI with ANSI colour codes |
| MainApp.java | Controller — main game loop and scene switching |

##  DSA & OOP Concepts Used
- HashSet — O(1) letter lookup for guessed letters
- LinkedList — insertion-order wrong guess history
- char[] array — secret word storage with O(1) per-letter access
- 2D String[][] array — 7 gallows art stages
- ArrayList + Collections.sort() — Timsort leaderboard sorting
- Comparable interface — natural ordering of score entries
- MVC Pattern — GameEngine (Model), Renderer (View), MainApp (Controller)
- Encapsulation — all fields private with getters/setters
- Enum — GuessOutcome (CORRECT, WRONG, WIN, LOSE, ALREADY_GUESSED)

##  How to Run
​```
javac -d out com\btech\hangman\*.java
java -cp out com.btech.hangman.MainApp
​```
Or just double-click run.bat

##  Requirements
- Java 17 or higher
- Windows CMD / PowerShell / Any terminal