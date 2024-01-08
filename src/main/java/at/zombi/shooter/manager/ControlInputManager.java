package at.zombi.shooter.manager;

import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ControlInputManager {
    private static ControlInputManager controlInputManager;

    private boolean forward = false;
    private boolean backward = false;
    private boolean left = false;
    private boolean right = false;
    private boolean pauseGame = false;
    private Map<MouseButton, Vector2D> mouseClicks = new ConcurrentHashMap<>();

    private ControlInputManager() {
        Scene scene = SceneManager.getSceneManager().getMainPane().getScene();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent) -> handleKeyEvent(keyEvent, true));
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, (mouseEvent) -> handleMouseEvent(mouseEvent));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (keyEvent) -> handleKeyEvent(keyEvent, false));
    }

    public static ControlInputManager getControlInputManager() {
        if (controlInputManager == null) {
            controlInputManager = new ControlInputManager();
        }
        return controlInputManager;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isBackward() {
        return backward;
    }

    public void setBackward(boolean backward) {
        this.backward = backward;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isPauseGame() {
        return pauseGame;
    }

    public void setPauseGame(boolean pauseGame) {
        this.pauseGame = pauseGame;
    }

    private void handleMouseEvent(MouseEvent mouseEvent) {
        mouseClicks.put(mouseEvent.getButton(), new Vector2D(mouseEvent.getSceneX(), mouseEvent.getSceneY()));
    }

    private void handleKeyEvent(KeyEvent keyEvent, boolean state) {
        String character = keyEvent.getCode().getName().toUpperCase();
        switch (character) {
            case "W":
                setForward(state);
                break;
            case "A":
                setLeft(state);
                break;
            case "S":
                setBackward(state);
                break;
            case "D":
                setRight(state);
                break;
            case "ESC":
                setPauseGame(state);
                break;
            default:
                break;
        }
    }

    public Vector2D hasLeftClicked() {
        Vector2D target = mouseClicks.get(MouseButton.PRIMARY);
        if (target != null) {
            mouseClicks.remove(MouseButton.PRIMARY);
        }
        return target;
    }
}
