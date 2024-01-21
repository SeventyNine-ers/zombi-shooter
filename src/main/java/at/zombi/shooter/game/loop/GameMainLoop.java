package at.zombi.shooter.game.loop;

import at.zombi.shooter.game.elements.GameObject;
import at.zombi.shooter.game.elements.Zombie;
import at.zombi.shooter.game.state.GameState;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.manager.ControlInputManager;

/**
 * Diese Klasse beinhaltet die Logik in Bezug auf die Game-Loop in der das Spiel agiert.
 *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */

public class GameMainLoop {
    public static final int TARGET_TICK_RATE = 60; // Game ticks per second

    private final Thread gameThread;
    private boolean running = false;

    public GameMainLoop() {
        this.gameThread = new Thread(this::mainLoop);
        this.gameThread.setPriority(7);
        this.gameThread.setDaemon(true); // Ends thread when the applications closes
    }

    public void startMainLoop() {
        this.running = true;
        this.gameThread.start();
    }

    public void stopMainLoop() {
        this.running = false;
        if(gameThread.isAlive()) {
            this.gameThread.stop(); // Should not be necessary but safe is safe.
        }
    }

    private void mainLoop() {
        while(running) {
            long startTime = System.currentTimeMillis();
            GameStateManager gameStateManager = GameStateManager.getGameStateManager();

            if (GameState.RUNNING.equals(gameStateManager.getState())) {
                DeltaTimeManager.getDeltaTimeManager().update();
                gameStateManager.getGameMap().getAllGameObjects()
                        .forEach(GameObject::update);
                gameStateManager.updateTimeRemaining();
            } else {
                sleepMillis(100);   //Für CPU
            }
            handleControlInputs();

            checkIfGameIsWon();
            spawnNewEnemies();
            ensureMinLoopTime(startTime);
        }
    }

    private void spawnNewEnemies() {
        // TODO Make a better map and spawn enemies properly
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        long enemies = gameStateManager.getGameMap().getCollidableGameObjects().stream()
                .filter(solid -> solid instanceof Zombie)
                .count();
        if (enemies < 40) {
            gameStateManager.getGameMap().add(new Zombie(new Vector2D(800 + (Math.random() * 100) - 50, 960 + (Math.random() * 100) - 50)));
        }
    }

    private void checkIfGameIsWon() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        // Marke game as won if time is up
        if (gameStateManager.getTimeRemaining() <= 0) {
            gameStateManager.setState(GameState.WON);
        }
    }

    private void ensureMinLoopTime(long startTime) {
        // We make sure that delta time does not get to small.
        long waitTime = (System.currentTimeMillis() - startTime) - 2;
        if (waitTime < 0) {
            sleepMillis(-waitTime);
        }
    }

    private void handleControlInputs() {
        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        if (controlInputManager.isPauseGame()) {
            gameStateManager.setState(GameState.PAUSED);
        }
    }

    private void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }
}
