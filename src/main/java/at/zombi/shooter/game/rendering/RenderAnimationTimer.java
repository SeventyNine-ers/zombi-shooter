package at.zombi.shooter.game.rendering;

import javafx.animation.AnimationTimer;

import java.util.function.Consumer;

/**
 * This class contains the logic for rendering elements according to the frames which are calles in the GameLoop
 * <p>
 * Author: Alexander Doubrava
 * Date: 06.01.2024
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
