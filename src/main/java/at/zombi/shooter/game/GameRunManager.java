package at.zombi.shooter.game;

import at.zombi.shooter.game.loop.GameMainLoop;
import at.zombi.shooter.game.rendering.GameRenderer;
import at.zombi.shooter.game.state.GameStateManager;
import javafx.scene.layout.Pane;

public class GameRunManager {
    private static GameRunManager gameRunManager;

    private GameRenderer gameRenderer;
    private GameMainLoop gameMainLoop;

    private GameRunManager() {
        // Singleton
    }

    public static synchronized GameRunManager getGameRunManager() {
        if (gameRunManager == null) {
            gameRunManager = new GameRunManager();
        }

        return gameRunManager;
    }

    public synchronized void startGame(Pane gameView) {
        stopGame();
        GameStateManager.getGameStateManager().resetGameState();

        gameRenderer = new GameRenderer(gameView);
        gameRenderer.startRenderLoop();

        gameMainLoop = new GameMainLoop();
        gameMainLoop.startMainLoop();
    }

    public synchronized void stopGame() {
        if (gameRenderer != null) {
            gameRenderer.stopRenderLoop();
        }
        if (gameMainLoop != null) {
            gameMainLoop.stopMainLoop();
        }
    }
}
