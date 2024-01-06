package at.zombi.shooter.game.state;

public class GameStateManager {
    private static GameStateManager gameStateManager;

    private boolean firstStart = true;
    private GameState state = GameState.PAUSED;
    private long timeRemaining;
    private GameMap gameMap;

    private GameStateManager() {
        // Singleton
        resetGameState();
    }

    public static synchronized GameStateManager getGameStateManager() {
        if (gameStateManager == null) {
            gameStateManager = new GameStateManager();
        }
        return gameStateManager;
    }

    public void resetGameState() {
        setTimeRemaining(300);
        this.gameMap = new GameMap();
        this.gameMap.initMap();
        state = GameState.RUNNING;
        this.firstStart = true;
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

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public boolean isFirstStart() {
        return firstStart;
    }

    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }
}
