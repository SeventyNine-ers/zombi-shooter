package at.zombi.shooter.game.loop;

/**
 * Diese Klasse beinhaltet die Logik des Zeitablaufs und Zeitupdates im Bezug auf das Spielerlebnis.
 *
 * Ersteller: Alexander Doubrava, Patrick Kristof
 * Datum: 06.01.2024
 */
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
            lastTick = System.currentTimeMillis();
            return;
        }
        long currentTick = System.currentTimeMillis();

        double tickTime = currentTick - lastTick;
        double targetTickTime = 1000d / (double) GameMainLoop.TARGET_TICK_RATE; // because 1000ms per second and tick rate is per secound

        deltaTime = tickTime / targetTickTime;

        // Ignore to extreme delays in tick time to prevent breaking stuff
        if (deltaTime > 50) {
            deltaTime = 1;
        }

        this.lastTick = currentTick;
    }

    public static synchronized DeltaTimeManager getDeltaTimeManager() {
        if (deltaTimeManager == null) {
            deltaTimeManager = new DeltaTimeManager();
        }

        return deltaTimeManager;
    }

}
