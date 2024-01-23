package at.zombi.shooter.game.loop;

import at.zombi.shooter.game.elements.*;
import at.zombi.shooter.game.state.*;
import at.zombi.shooter.game.util.*;
import at.zombi.shooter.manager.ControlInputManager;
import at.zombi.shooter.manager.HighScoreManager;

import java.util.List;

/**
 * Diese Klasse beinhaltet die Logik in Bezug auf die Game-Loop in der das Spiel agiert.
 * <p>
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

            if(GameState.RUNNING.equals(gameStateManager.getState())) {
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
        // TODO Make a better map and spawn enemies properly
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        long enemies = gameStateManager.getGameMap().getCollidableGameObjects().stream()
            .filter(solid -> solid instanceof Zombie)
            .count();
        List<GameObject> spawnPoints = gameStateManager.getGameMap().getAllGameObjects().stream()
            .filter(solid -> solid instanceof Spawner)
            .toList();
        if(enemies < 40) {
            for(GameObject spawnPoint : spawnPoints) {
                // gameStateManager.getGameMap().add(new Zombie(new Vector2D(800 + (Math.random() * 100) - 50, 960 + (Math.random() * 100) - 50)));
                gameStateManager.getGameMap().add(new Zombie(new Vector2D(spawnPoint.getPosition().x, spawnPoint.getPosition().y + (Math.random() * (250 - 100) + 100))));
            }
        }
    }

    private void checkIfGameIsWon() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        Player player = gameStateManager.getGameMap().getPlayer();
        // Mark game as won if time is up
        if(gameStateManager.getTimeRemaining() <= 0) {
            if(gameStateManager.getState() == GameState.RUNNING) {
                // Punkte Anrechnung für die ueberlebte Zeit
                player.updateLivesScore(player.getHealth());
                // Speichern des Scores bei gewonnenem Spiel
                HighscoreEntry saveScore = new HighscoreEntry(PlayerData.getInstance().getPlayerName(), player.getScore());
                HighScoreManager.addHighscoreEntry(saveScore, HighScoreManager.loadHighscores());
            }
            gameStateManager.setState(GameState.WON);
        }
    }

    private void ensureMinLoopTime(long startTime) {
        // We make sure that delta time does not get to small.
        long waitTime = (System.currentTimeMillis() - startTime) - 2;
        if(waitTime < 0) {
            sleepMillis(-waitTime);
        }
    }

    private void handleControlInputs() {
        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        if(controlInputManager.isPauseGame()) {
            gameStateManager.setState(GameState.PAUSED);
        }
    }

    private void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
        }
    }
}
