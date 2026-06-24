package com.btech.hangman;

/**
 * HangmanArt.java
 *
 * OOP Role: Utility class — stores all 7 ASCII art stages of the hangman.
 *           Pure data, no logic.
 *
 * DSA Role: String[][] — 2D array where:
 *   - First dimension  = stage (0 = full gallows only, 6 = fully drawn man)
 *   - Second dimension = lines of that stage's art
 *
 * Stage index = MAX_LIVES - livesLeft
 * (0 wrong = stage 0, 1 wrong = stage 1 ... 6 wrong = stage 6)
 */
public class HangmanArt {

    /**
     * DSA: 2D String array — 7 stages × 8 lines each.
     * Indexed as STAGES[wrongGuessCount][lineNumber].
     */
    private static final String[][] STAGES = {
        // Stage 0 — 0 wrong guesses — just the gallows
        {
            "       ┌───┐   ",
            "       │   │   ",
            "           │   ",
            "           │   ",
            "           │   ",
            "           │   ",
            "    ════════   "
        },
        // Stage 1 — 1 wrong guess — head appears
        {
            "       ┌───┐   ",
            "       │   │   ",
            "       O   │   ",
            "           │   ",
            "           │   ",
            "           │   ",
            "    ════════   "
        },
        // Stage 2 — 2 wrong guesses — body appears
        {
            "       ┌───┐   ",
            "       │   │   ",
            "       O   │   ",
            "       │   │   ",
            "           │   ",
            "           │   ",
            "    ════════   "
        },
        // Stage 3 — 3 wrong guesses — left arm appears
        {
            "       ┌───┐   ",
            "       │   │   ",
            "       O   │   ",
            "      /│   │   ",
            "           │   ",
            "           │   ",
            "    ════════   "
        },
        // Stage 4 — 4 wrong guesses — right arm appears
        {
            "       ┌───┐   ",
            "       │   │   ",
            "       O   │   ",
            "      /|\\  │   ",
            "           │   ",
            "           │   ",
            "    ════════   "
        },
        // Stage 5 — 5 wrong guesses — left leg appears
        {
            "       ┌───┐   ",
            "       │   │   ",
            "       O   │   ",
            "      /|\\  │   ",
            "      /    │   ",
            "           │   ",
            "    ════════   "
        },
        // Stage 6 — 6 wrong guesses — right leg — GAME OVER
        {
            "       ┌───┐   ",
            "       │   │   ",
            "       O   │   ",
            "      /|\\  │   ",
            "      / \\  │   ",
            "           │   ",
            "    ════════   "
        }
    };

    /**
     * Returns the art lines for the given number of wrong guesses.
     *
     * @param wrongCount number of wrong guesses (0–6)
     * @return String[] of art lines for that stage
     */
    public static String[] getStage(int wrongCount) {
        int idx = Math.min(wrongCount, STAGES.length - 1);
        return STAGES[idx];
    }

    /** Total number of stages (0 through 6). */
    public static int totalStages() { return STAGES.length; }
}
