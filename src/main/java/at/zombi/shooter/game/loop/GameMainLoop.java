package at.zombi.shooter.game.loop;

import at.zombi.shooter.game.elements.*;
import at.zombi.shooter.game.state.*;
import at.zombi.shooter.game.util.*;
import at.zombi.shooter.manager.ControlInputManager;
import at.zombi.shooter.manager.HighScoreManager;

/**
 *This class contains the logic in the GameMainLoop which runs while the GameState is RUNNING
 * acts as updater for different elements
 *
 * Author: Alexander Doubrava
 * Date: 06.01.2024
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
        /**
         * The mainLoop() states everything that happens while the game is running
         * During the gameState.RUNNING it updates the Time on the right upper corner
         * as well as updates every object which is moving inside the game
         * with the help of the deltaTimeManager!
         */
        while(running) {
            long startTime = System.currentTimeMillis();
            GameStateManager gameStateManager = GameStateManager.getGameStateManager();

            if (GameState.RUNNING.equals(gameStateManager.getState())) {
                DeltaTimeManager.getDeltaTimeManager().update();
                gameStateManager.getGameMap().getAllGameObjects()
                        .forEach(GameObject::update);
                gameStateManager.updateTimeRemainingAndPlayerScore();
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
        /**
         * spawnNewEnemies() currently spawns enemies in 2 positions (current Map Spawner)
         * every time the mainLoop() goes through
         */
        // TODO Make a better map and spawn enemies properly
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        long enemies = gameStateManager.getGameMap().getCollidableGameObjects().stream()
                .filter(solid -> solid instanceof Zombie)
                .count();
        //to ensure there are at least 40 zombies currently active
        if (enemies < 40) {
            gameStateManager.getGameMap().add(new Zombie(new Vector2D(800 + (Math.random() * 100) - 50, 960 + (Math.random() * 100) - 50)));
        }
    }

    private void checkIfGameIsWon() {
        /**
         * checkIfGameIsWon() checks the current game state,
         * calculates the points and saves them in the HighscoreEntry
         */
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        Player player = gameStateManager.getGameMap().getPlayer();
        // Mark game as won if time is up
        if (gameStateManager.getTimeRemaining() <= 0) {
            if(gameStateManager.getState() == GameState.RUNNING) {
                // Points for the lives that remain after winning the game
                player.updateLivesScore(player.getHealth());
                // Saving the score to the HighScoreManager
                HighscoreEntry saveScore = new HighscoreEntry(PlayerData.getInstance().getPlayerName(), player.getScore());
                HighScoreManager.addHighscoreEntry(saveScore, HighScoreManager.loadHighscores());
            }
            //gameState changed to WON to ensure the WON-Screen is activated
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
        /**
         * Goes through the controlInputManager to see which inputs are currently
         * put in by the player and acts accordingly
         */
        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        //Pauses the game if the ESC button is pressed, according to the controlInputManager
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
