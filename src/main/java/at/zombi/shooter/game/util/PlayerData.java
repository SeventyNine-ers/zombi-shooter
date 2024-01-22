package at.zombi.shooter.game.util;

public class PlayerData {
    private static final PlayerData instance = new PlayerData();
    private String playerName;

    private PlayerData() {
        // Privater Konstruktor -> Singleton, es kann nur eine Instanz geben.
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
