package io.github.arnabmaji19.snakegame.model;

public class ScoreBoard {

    private static final int STEP = 5;

    private int score = 0;

    public void reset() {
        score = 0;
    }

    public void increment() {
        score += STEP;
    }

    public int get() {
        return score;
    }
}
