package at.zombi.shooter.game.util;

import java.io.Serializable;

public class HighscoreEntry implements Serializable {
    private String playerName;
    private int score;

    public HighscoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player: " + playerName + ", Score: " + score;
    }
}
