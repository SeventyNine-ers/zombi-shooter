package at.zombi.shooter.game.state;

public class GameStateManager {

    private static GameStateManager gameStateManager;

    private boolean paused = false;
    private boolean lost = false;
    private boolean won = false;
    private long timeRemaining = 300;

    private GameMap gameMap = new GameMap();

    private GameStateManager() {
        // Singleton
    }

    public static synchronized GameStateManager getGameStateManager() {
        if (gameStateManager == null) {
            gameStateManager = new GameStateManager();
        }
        return gameStateManager;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean hasLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean hasWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
