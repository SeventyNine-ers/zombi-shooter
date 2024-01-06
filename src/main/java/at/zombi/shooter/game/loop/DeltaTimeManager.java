package at.zombi.shooter.game.loop;

public class DeltaTimeManager {
    private static DeltaTimeManager deltaTimeManager;

    private long lastTick = 0;
    private double deltaTime = 1;

    private DeltaTimeManager() {
        // Singleton
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void update() {
        if (lastTick == 0) {
            return;
        }
        long currentTick = System.currentTimeMillis();

        double tickTime = currentTick - lastTick;
        double targetTickTime = 1000d / (double) GameMainLoop.TARGET_TICK_RATE; // because 1000ms per second and tick rate is per secound

        deltaTime = tickTime / targetTickTime;

        this.lastTick = currentTick;
    }

    public static synchronized DeltaTimeManager getDeltaTimeManager() {
        if (deltaTimeManager == null) {
            deltaTimeManager = new DeltaTimeManager();
        }

        return deltaTimeManager;
    }

}
