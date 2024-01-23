package at.zombi.shooter.game.loop;

/**
 * This class contains the Logic for the time management in relation to DeltaTime
 * acts out updates on the game in DeltaTime-manner
 * <p>
 * Authors: Alexander Doubrava, Patrick Kristof
 * Date: 06.01.2024
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
        /**
         * DeltaTimeManager agiert so, dass bei jedem "Tick" die Objekte upgedatet werden
         * Damit keine Performance-Probleme bei schwachen PCs auftauchen werden die Objekte nicht per "Tick" um Pixel bewegt
         * Die DeltaTime sorgt dafür, dass das Objekt bei schnellerer Zeit des Rechners weniger schnell läuft,
         * dafür bei langsamerer Zeit des Rechners schneller läuft.
         */
        if(lastTick == 0) {
            lastTick = System.currentTimeMillis();
            return;
        }
        long currentTick = System.currentTimeMillis();

        double tickTime = currentTick - lastTick;
        double targetTickTime = 1000d / (double) GameMainLoop.TARGET_TICK_RATE; // because 1000ms per second and tick rate is per secound

        deltaTime = tickTime / targetTickTime;

        // Ignore to extreme delays in tick time to prevent breaking stuff
        if(deltaTime > 50) {
            deltaTime = 1;
        }

        this.lastTick = currentTick;
    }

    public static synchronized DeltaTimeManager getDeltaTimeManager() {
        if(deltaTimeManager == null) {
            deltaTimeManager = new DeltaTimeManager();
        }

        return deltaTimeManager;
    }

}
