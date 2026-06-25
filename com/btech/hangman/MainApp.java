package com.btech.hangman;

import java.util.Scanner;

public class MainApp {

    private final Renderer    renderer    = new Renderer();
    private final Leaderboard leaderboard = new Leaderboard();
    private final GameEngine  engine      = new GameEngine();
    private final Scanner     scanner     = new Scanner(System.in);

    public static void main(String[] args) {
        new MainApp().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            renderer.renderMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> playGame();
                case "2" -> showHelp();
                case "3" -> showLeaderboard();
                case "4" -> {
                    running = false;
                    System.out.println("\n  Thanks for playing Hangman! \n");
                }
                default -> renderer.printMessage("Invalid choice. Enter 1–4.");
            }
        }
        scanner.close();
    }

    private void playGame() {
        engine.startNewGame();
        long startTime = System.currentTimeMillis();

        while (!engine.isGameOver()) {
            int elapsed = (int)((System.currentTimeMillis() - startTime) / 1000);
            renderer.renderGame(engine, elapsed);

            String input = scanner.nextLine().trim().toUpperCase();

            // Quit to menu
            if (input.equals("QUIT") || input.equals("Q")) return;

            // Validate input — must be a single letter
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                renderer.printMessage("Please enter a single letter (A–Z).");
                pause(800);
                continue;
            }

            char letter = input.charAt(0);

            GameEngine.GuessOutcome outcome = engine.guess(letter);

            switch (outcome) {
                case ALREADY_GUESSED -> {
                    renderer.printMessage("You already guessed '" + letter + "'! Try another.");
                    pause(800);
                }
                case CORRECT -> {
                    renderer.printMessage("✅ '" + letter + "' is in the word!");
                    pause(600);
                }
                case WRONG -> {
                    renderer.printMessage("❌ '" + letter + "' is not in the word!");
                    pause(600);
                }
                case WIN, LOSE -> {
                   
                }
            }
        }

        int finalTime = (int)((System.currentTimeMillis() - startTime) / 1000);

        if (engine.isWordComplete()) {
            renderer.renderWin(engine, finalTime);
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) name = "Anonymous";
            leaderboard.addEntry(new ScoreEntry(
                    name,
                    engine.getSecretWord(),
                    engine.getWrongCount(),
                    finalTime));
            showLeaderboard();
        } else {
            renderer.renderLoss(engine, finalTime);
            scanner.nextLine();
        }
    }

    private void showHelp() {
        renderer.renderHelp();
        scanner.nextLine();
    }

    private void showLeaderboard() {
        renderer.renderLeaderboard(leaderboard.getEntries());
        scanner.nextLine();
    }
    private void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
