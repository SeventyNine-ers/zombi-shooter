package at.zombi.shooter.game.util;

import java.io.Serializable;

/**
 * This class saves the values like playerName and the current score in the HighScoreEntry
 * which is later used in the HighScoreManager
 *
 * Author: Patrick Kristof
 * Date: 21.01.2024
 */

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
