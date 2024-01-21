package at.zombi.shooter.game.rendering;

import at.zombi.shooter.game.state.GameStateManager;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Diese Klasse beinhaltet die Logik der gerenderten Elemente im Blick der Spieler-Kamera.
 * *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */

public class GameRenderer {

    private Pane gameView;
    private RenderAnimationTimer animationTimer;

    private Overlay overlay;
    private Camera camera;

    public GameRenderer(Pane gameView) {
        this.gameView = gameView;
        this.overlay = new Overlay();
        this.camera = new Camera(GameStateManager.getGameStateManager().getGameMap().getPlayer().getPosition());
    }

    //TODO: Background before camera-element

    public void renderLoop(long nowInMillis) {
        List<Node> screenElements = Stream.of(getCamera().render(), getOverlay().render())
                .flatMap(Collection::stream)
                .toList();

        // TODO Adjust screenElements to screen size (currently fixed to 1280x720p)

        gameView.getChildren().clear();
        gameView.getChildren().addAll(screenElements);
    }

    public void startRenderLoop() {
        stopRenderLoop();
        this.animationTimer = new RenderAnimationTimer(this::renderLoop);
        this.animationTimer.start();
    }

    public void stopRenderLoop() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    public Overlay getOverlay() {
        return overlay;
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    protected void finalize() {
        // We make sure that the animationTimer is removed from JavaFX cycle.
        // This should not be necessary but better safe than sorry.
        stopRenderLoop();
    }
}
