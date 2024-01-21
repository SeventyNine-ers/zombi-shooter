package at.zombi.shooter.game.state;

/**
 * Diese Klasse beinhaltet die Logik in Bezug auf die GameState, welche geändert wird.
 * Hier wird auf die einzelnen States reagiert und eventuell resetted.
 *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */
public class GameStateManager {
    private static GameStateManager gameStateManager;

    private boolean firstStart = true;
    private GameState state = GameState.PAUSED;
    private long timeRemaining;
    private long lastUnixSecond;
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
        state = GameState.PAUSED;
        this.firstStart = true;
    }

    public void updateTimeRemaining() {
        final long currentUnixSecond = System.currentTimeMillis() / 1000;
        if (lastUnixSecond != currentUnixSecond) {
            setTimeRemaining(getTimeRemaining() - 1); // We count fix down by 1 to prevent counting paused state.
            lastUnixSecond = currentUnixSecond;
        }
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
