package com.btech.hangman;

import java.util.Random;

/**
 * WordBank.java
 *
 * OOP Role: Utility class — owns the word list with hints.
 *           No game logic, no I/O.
 *
 * DSA Role: Two parallel String[] arrays (words + hints) — O(1) random access.
 *           Same index in both arrays = one word-hint pair.
 */
public class WordBank {

    private static final String[] WORDS = {
        "APPLE",  "MANGO",   "GRAPE",   "LEMON",   "PEACH",
        "TIGER",  "ELEPHANT","GIRAFFE", "PENGUIN", "DOLPHIN",
        "GUITAR", "PIANO",   "VIOLIN",  "TRUMPET", "DRUMS",
        "PYTHON", "JAVA",    "LINUX",   "GOOGLE",  "MATRIX",
        "CASTLE", "KNIGHT",  "WIZARD",  "DRAGON",  "SHIELD",
        "OCEAN",  "DESERT",  "FOREST",  "MOUNTAIN","VOLCANO",
        "DOCTOR", "NURSE",   "LAWYER",  "ENGINEER","PILOT",
        "PIZZA",  "BURGER",  "SUSHI",   "PASTA",   "TACOS",
        "EARTH",  "SATURN",  "JUPITER", "MERCURY", "NEPTUNE",
        "CHESS",  "POKER",   "TENNIS",  "CRICKET", "SOCCER"
    };

    private static final String[] HINTS = {
        "A common red fruit 🍎",         "A tropical yellow fruit 🥭",
        "Small purple fruit 🍇",         "Sour yellow citrus 🍋",
        "Fuzzy pink fruit 🍑",           "Striped jungle cat 🐯",
        "Largest land animal 🐘",        "Tallest animal on earth 🦒",
        "Black and white bird 🐧",       "Intelligent sea mammal 🐬",
        "Six-stringed instrument 🎸",    "Black and white keys 🎹",
        "Bowed string instrument 🎻",    "Brass wind instrument 🎺",
        "Percussion instrument 🥁",      "Popular programming language 🐍",
        "Write once run anywhere ☕",    "Open source OS 🐧",
        "World's biggest search engine 🔍", "Famous sci-fi movie 🎬",
        "Medieval fortified building 🏰","Chess piece on horseback ♞",
        "Magical spell caster 🧙",       "Mythical fire-breathing beast 🐉",
        "Protective armor piece 🛡️",     "Covers 71% of Earth 🌊",
        "Hot sandy biome 🏜️",           "Dense tree-filled biome 🌲",
        "Tallest landform on Earth ⛰️",  "Erupts with lava 🌋",
        "Treats sick people 👨‍⚕️",         "Assists doctors in hospitals 👩‍⚕️",
        "Practices law in court ⚖️",     "Builds things for society 🔧",
        "Flies planes in the sky ✈️",    "Italian circular dish 🍕",
        "Classic fast food sandwich 🍔", "Japanese rice dish 🍱",
        "Italian noodle dish 🍝",        "Mexican folded dish 🌮",
        "Our home planet 🌍",            "Has beautiful rings 🪐",
        "Largest planet 🔴",             "Closest planet to sun ☿",
        "Farthest planet 🔵",            "Board game of strategy ♟️",
        "Card game in casinos 🃏",       "Racket sport 🎾",
        "Popular bat and ball sport 🏏", "Most popular sport worldwide ⚽"
    };

    private static final Random RANDOM = new Random();

    
    public static String[] randomWordWithHint() {
        int idx = RANDOM.nextInt(WORDS.length);
        return new String[]{ WORDS[idx], HINTS[idx] };
    }

    public static int size() { return WORDS.length; }
}
