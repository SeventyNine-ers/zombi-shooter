package at.zombi.shooter.manager;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class ControlInputManager {
    private static ControlInputManager controlInputManager;

    private boolean forward = false;
    private boolean backward = false;
    private boolean left = false;
    private boolean right = false;
    private boolean pauseGame = false;

    private ControlInputManager() {
        Scene scene = SceneManager.getSceneManager().getMainPane().getScene();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent) -> handleKeyEvent(keyEvent, true));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (keyEvent) -> handleKeyEvent(keyEvent, false));
    }

    public static ControlInputManager getControlInputManager() {
        if(controlInputManager == null) {
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

    private void handleKeyEvent(KeyEvent keyEvent, boolean state){
        String character = keyEvent.getCode().getName().toUpperCase();
        switch (character){
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
}
