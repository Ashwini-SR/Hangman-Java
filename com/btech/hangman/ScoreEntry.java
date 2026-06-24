package com.btech.hangman;

/**
 * ScoreEntry.java
 *
 * OOP Role: Comparable Value Object — one leaderboard row.
 * DSA Role: Stored in ArrayList<ScoreEntry> sorted by Collections.sort()
 *           (Timsort O(n log n)). Fewer wrong guesses = better rank.
 */
public class ScoreEntry implements Comparable<ScoreEntry> {

    private final String name;
    private final String word;
    private final int    wrongGuesses;
    private final int    timeSeconds;

    public ScoreEntry(String name, String word, int wrongGuesses, int timeSeconds) {
        this.name         = name;
        this.word         = word;
        this.wrongGuesses = wrongGuesses;
        this.timeSeconds  = timeSeconds;
    }

    public String getName()         { return name;         }
    public String getWord()         { return word;         }
    public int    getWrongGuesses() { return wrongGuesses; }
    public int    getTimeSeconds()  { return timeSeconds;  }

    public String getFormattedTime() {
        return String.format("%d:%02d", timeSeconds / 60, timeSeconds % 60);
    }

    
    @Override
    public int compareTo(ScoreEntry o) {
        if (this.wrongGuesses != o.wrongGuesses)
            return Integer.compare(this.wrongGuesses, o.wrongGuesses);
        return Integer.compare(this.timeSeconds, o.timeSeconds);
    }

    public String toCsvLine() {
        return name + "," + word + "," + wrongGuesses + "," + timeSeconds;
    }

    public static ScoreEntry fromCsvLine(String line) {
        try {
            String[] p = line.split(",", 4);
            if (p.length < 4) return null;
            return new ScoreEntry(p[0].trim(), p[1].trim(),
                    Integer.parseInt(p[2].trim()),
                    Integer.parseInt(p[3].trim()));
        } catch (NumberFormatException e) { return null; }
    }

    @Override
    public String toString() {
        return String.format("%-12s %-10s %d wrong  %s",
                name, word, wrongGuesses, getFormattedTime());
    }
}
