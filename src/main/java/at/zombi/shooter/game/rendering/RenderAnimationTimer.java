package at.zombi.shooter.game.rendering;

import javafx.animation.AnimationTimer;
import java.util.function.Consumer;
/**
 * Diese Klasse beinhaltet die Logik des Renderns von Elementen im Bezug auf die TimeLoop / GameLoop
 *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */

public class RenderAnimationTimer extends AnimationTimer {

    Consumer<Long> renderLoopMethode;

    public RenderAnimationTimer(Consumer<Long> renderLoopMethode) {
        this.renderLoopMethode = renderLoopMethode;
    }


    /**
     * This method needs to be overridden by extending classes. It is going to
     * be called in every frame while the {@code AnimationTimer} is active.
     *
     * @param now The timestamp of the current frame given in nanoseconds. This
     *            value will be the same for all {@code AnimationTimers} called
     *            during one frame.
     */
    @Override
    public void handle(long now) {
        renderLoopMethode.accept(now);
    }
}
