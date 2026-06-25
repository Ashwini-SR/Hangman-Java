package com.btech.hangman;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Renderer.java
 *
 * OOP Role: View in MVC — ALL terminal output lives here. Zero game logic.
 *
 * Renders:
 *   - Main menu
 *   - Game screen (gallows art + masked word + keyboard + hint)
 *   - Win / Loss screens
 *   - Leaderboard table
 */
public class Renderer {

    
    private static final String RESET  = "\u001B[0m";
    private static final String BOLD   = "\u001B[1m";
    private static final String DIM    = "\u001B[2m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED    = "\u001B[31m";
    private static final String CYAN   = "\u001B[36m";
    private static final String BRIGHT = "\u001B[93m";

  
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void renderMenu() {
        clearScreen();
        System.out.println(BOLD + YELLOW);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║        HANGMAN GAME              ║");
        System.out.println("  ╚══════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println("  1.  Play Game");
        System.out.println("  2.  How to Play");
        System.out.println("  3.  Leaderboard");
        System.out.println("  4.  Quit");
        System.out.println();
        System.out.print("  Enter choice: ");
    }

   
    public void renderHelp() {
        clearScreen();
        System.out.println(BOLD + CYAN + "  HOW TO PLAY HANGMAN" + RESET);
        System.out.println("  ─────────────────────────────────────");
        System.out.println("  • A secret word is chosen randomly");
        System.out.println("  • You see blanks: _ _ _ _ _");
        System.out.println("  • Guess one letter at a time");
        System.out.println("  • Correct guess → letter revealed");
        System.out.println("  • Wrong guess   → body part drawn");
        System.out.println("  • 6 wrong guesses = GAME OVER");
        System.out.println("  • Reveal all letters = YOU WIN!");
        System.out.println();
        System.out.println("  Body parts drawn per wrong guess:");
        System.out.println("    1. Head    4. Right Arm");
        System.out.println("    2. Body    5. Left Leg");
        System.out.println("    3. Left Arm  6. Right Leg");
        System.out.println();
        System.out.println("  A hint is shown to help you!");
        System.out.println();
        System.out.print("  Press Enter to go back...");
    }

    // ── Game screen ───────────────────────────────────────────────────────────

    /**
     * Renders the full game screen:
     *   - Header with lives
     *   - Gallows ASCII art (from HangmanArt)
     *   - Masked word display
     *   - Wrong guesses list
     *   - On-screen alphabet keyboard
     *   - Hint
     *   - Input prompt
     */
    public void renderGame(GameEngine engine, int elapsedSeconds) {
        clearScreen();

        int wrongCount = engine.getWrongCount();
        int livesLeft  = engine.getLivesLeft();

        
        System.out.println(BOLD + "  ══════════════════════════════════════" + RESET);
        System.out.printf(BOLD + "  HANGMAN   Lives: " + RED + "%d/%d" + RESET +
                          BOLD + "   Time: %s%n" + RESET,
                livesLeft, GameEngine.MAX_LIVES,
                formatTime(elapsedSeconds));
        System.out.println(BOLD + "  ══════════════════════════════════════" + RESET);
        System.out.println();

        
        String[] art = HangmanArt.getStage(wrongCount);

        // Print gallows lines
        for (String line : art) {
            System.out.println("  " + RED + line + RESET);
        }

        System.out.println();

        
        System.out.println("  " + BOLD + BRIGHT
                + "  " + engine.getMaskedWord() + RESET);
        System.out.println();

        
        LinkedList<Character> wrong = engine.getWrongGuesses();
        if (wrong.isEmpty()) {
            System.out.println("  Wrong guesses: " + DIM + "none yet" + RESET);
        } else {
            StringBuilder wb = new StringBuilder("  Wrong guesses: " + RED);
            for (char c : wrong) wb.append(c).append(' ');
            System.out.println(wb + RESET);
        }
        System.out.println();

        
        renderKeyboard(engine.getGuessedLetters(), engine.getWrongGuesses());
        System.out.println();

      
        System.out.println("   Hint: " + CYAN + engine.getHint() + RESET);
        System.out.println();

       
        if (!engine.isGameOver()) {
            System.out.print("  Enter a letter (or QUIT): ");
        }
    }

    /**
     * Renders A–Z keyboard with colour coding:
     *   Green  = guessed correctly (in word)
     *   Red    = guessed wrongly (not in word)
     *   Normal = not yet guessed
     */
    private void renderKeyboard(Set<Character> guessed, LinkedList<Character> wrong) {
        System.out.print("  ");
        int count = 0;
        for (char c : ALPHABET.toCharArray()) {
            if (!guessed.contains(c)) {
                System.out.print(BOLD + " " + c + " " + RESET);
            } else if (wrong.contains(c)) {
                System.out.print(RED + DIM + " " + c + " " + RESET);
            } else {
                System.out.print(GREEN + BOLD + " " + c + " " + RESET);
            }
            count++;
            if (count == 13) System.out.print("\n  "); 
        }
        System.out.println();
    }

  

    public void renderWin(GameEngine engine, int timeSeconds) {
        clearScreen();
       
        String[] art = HangmanArt.getStage(engine.getWrongCount());
        for (String line : art) System.out.println("  " + RED + line + RESET);

        System.out.println();
        System.out.println(BOLD + GREEN + "  🎉 YOU WIN! Excellent!" + RESET);
        System.out.println();
        System.out.println("  Word    : " + BOLD + BRIGHT + engine.getSecretWord() + RESET);
        System.out.printf ("  Wrong   : %d/%d%n", engine.getWrongCount(), GameEngine.MAX_LIVES);
        System.out.printf ("  Time    : %s%n%n", formatTime(timeSeconds));
        System.out.print  ("  Enter your name to save score: ");
    }

    public void renderLoss(GameEngine engine, int timeSeconds) {
        clearScreen();
        String[] art = HangmanArt.getStage(GameEngine.MAX_LIVES);
        for (String line : art) System.out.println("  " + RED + line + RESET);

        System.out.println();
        System.out.println(BOLD + RED + "  💀 GAME OVER! The man is hanged." + RESET);
        System.out.println();
        System.out.println("  The word was: " + BOLD + BRIGHT + engine.getSecretWord() + RESET);
        System.out.printf ("  Time: %s%n%n", formatTime(timeSeconds));
        System.out.print  ("  Press Enter to continue...");
    }

  
    public void renderLeaderboard(List<ScoreEntry> entries) {
        clearScreen();
        System.out.println(BOLD + YELLOW + "    HANGMAN LEADERBOARD" + RESET);
        System.out.println("  ══════════════════════════════════════════");
        System.out.printf ("  %-4s %-12s %-10s %-8s %s%n",
                "Rank","Name","Word","Wrong","Time");
        System.out.println("  ──────────────────────────────────────────");
        if (entries.isEmpty()) {
            System.out.println("  No scores yet — play a game first!");
        } else {
            for (int i = 0; i < entries.size(); i++) {
                ScoreEntry e = entries.get(i);
                String medal = i == 0 ? " " : i == 1 ? " " : i == 2 ? " " : "  ";
                System.out.printf("  %s%-2d %-12s %-10s %d/%-5d  %s%n",
                        medal, i + 1,
                        e.getName(), e.getWord(),
                        e.getWrongGuesses(), GameEngine.MAX_LIVES,
                        e.getFormattedTime());
            }
        }
        System.out.println("  ══════════════════════════════════════════");
        System.out.println("\n  Press Enter to return...");
    }


    private String formatTime(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }

    public void printMessage(String msg) { System.out.println("  " + msg); }
}
