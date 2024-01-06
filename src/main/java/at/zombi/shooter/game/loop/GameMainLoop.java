package at.zombi.shooter.game.loop;

import at.zombi.shooter.game.elements.GameObject;
import at.zombi.shooter.game.state.GameState;
import at.zombi.shooter.game.state.GameStateManager;

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

            if (GameState.RUNNING.equals(GameStateManager.getGameStateManager().getState())) {
                GameStateManager.getGameStateManager().getGameMap().getAllGameObjects()
                        .forEach(GameObject::update);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }

            DeltaTimeManager.getDeltaTimeManager().update();

            // We make sure that delta time does not get to small.
            long waitTime = (System.currentTimeMillis() - startTime) - 5;
            if (waitTime < 0) {
                try {
                    Thread.sleep(-waitTime);
                } catch (InterruptedException e) {}
            }
        }
    }
}
