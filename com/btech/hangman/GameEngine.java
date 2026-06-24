package com.btech.hangman;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * GameEngine.java
 *
 * OOP Role: Model in MVC — owns all game state and rules. Zero I/O here.
 *
 * DSA Structures:
 *   char[]      — secret word as char array, O(1) index access per letter
 *   HashSet<Character> — guessed letters, O(1) average add/contains
 *   HashSet<Character> — wrong guesses subset (for display)
 *   LinkedList<Character> — ordered wrong guess history (insertion order)
 *   int         — lives remaining (starts at MAX_LIVES = 6)
 */
public class GameEngine {

    public static final int MAX_LIVES = 6;

    private char[] secretWord;

    private String hint;

   
    private final HashSet<Character> guessedLetters = new HashSet<>();

    
    private final LinkedList<Character> wrongGuesses = new LinkedList<>();

   
    private int livesLeft = MAX_LIVES;

   
    public GameEngine() { startNewGame(); }

    public void startNewGame() {
        String[] pair = WordBank.randomWordWithHint();
        secretWord    = pair[0].toCharArray();  
        hint          = pair[1];
        guessedLetters.clear();
        wrongGuesses.clear();
        livesLeft = MAX_LIVES;
    }

    
    public GuessOutcome guess(char letter) {
        letter = Character.toUpperCase(letter);

        
        if (guessedLetters.contains(letter)) {
            return GuessOutcome.ALREADY_GUESSED;
        }

       
        guessedLetters.add(letter);

       
        boolean found = false;
        for (char c : secretWord) {
            if (c == letter) { found = true; break; }
        }

        if (found) {
            
            return isWordComplete() ? GuessOutcome.WIN : GuessOutcome.CORRECT;
        } else {
            
            wrongGuesses.add(letter);   
            livesLeft--;
            return livesLeft == 0 ? GuessOutcome.LOSE : GuessOutcome.WRONG;
        }
    }

    
    public String getMaskedWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : secretWord) {
            sb.append(guessedLetters.contains(c) ? c : '_');
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public boolean isWordComplete() {
        for (char c : secretWord) {
            if (!guessedLetters.contains(c)) return false;
        }
        return true;
    }


    public String             getSecretWord()    { return new String(secretWord); }
    public String             getHint()          { return hint;                   }
    public int                getLivesLeft()     { return livesLeft;              }
    public int                getWrongCount()    { return wrongGuesses.size();    }
    public Set<Character>     getGuessedLetters(){ return guessedLetters;         }
    public LinkedList<Character> getWrongGuesses(){ return wrongGuesses;          }
    public boolean            isGameOver()       { return livesLeft <= 0 || isWordComplete(); }


    public enum GuessOutcome {
        CORRECT,         
        WRONG,           
        WIN,            
        LOSE,            
        ALREADY_GUESSED  
    }
}
