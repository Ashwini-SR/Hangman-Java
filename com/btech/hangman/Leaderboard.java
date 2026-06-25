package com.btech.hangman;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Leaderboard {

    private static final String FILE     = "hangman_scores.csv";
    public  static final int    MAX_ROWS = 10;

    private final ArrayList<ScoreEntry> entries = new ArrayList<>();

    public Leaderboard() { load(); }

    public void addEntry(ScoreEntry e) {
        entries.add(e);
        Collections.sort(entries);
        if (entries.size() > MAX_ROWS)
            entries.subList(MAX_ROWS, entries.size()).clear();
        save();
    }

    public List<ScoreEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    private void load() {
        entries.clear();
        Path p = Paths.get(FILE);
        if (!Files.exists(p)) return;
        try (BufferedReader br = new BufferedReader(new FileReader(p.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                ScoreEntry e = ScoreEntry.fromCsvLine(line);
                if (e != null) entries.add(e);
            }
        } catch (IOException ex) {
            System.err.println("[Leaderboard] " + ex.getMessage());
        }
        Collections.sort(entries);
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, false))) {
            pw.println(" Hangman Leaderboard — name,word,wrongGuesses,timeSeconds");
            for (ScoreEntry e : entries) pw.println(e.toCsvLine());
        } catch (IOException ex) {
            System.err.println("[Leaderboard] " + ex.getMessage());
        }
    }
}
