package at.zombi.shooter.game.util;

/**
 * This class is an instance for PlayerData to be used in the HighScoreEntry
 * <p>
 * Author: Patrick Kristof
 * Date: 21.01.2024
 */
public class PlayerData {
    private static final PlayerData instance = new PlayerData();
    private String playerName;

    private PlayerData() {
        //private constructor --> Singleton, so only one instance at a time
    }

    public static PlayerData getInstance() {
        return instance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
