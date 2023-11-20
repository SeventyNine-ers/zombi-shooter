package at.zombi.shooter.scene;

import at.zombi.shooter.manager.ControlInputManager;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameSceneController {

    @FXML
    private Pane gameView;

    @FXML
    public void initialize() {
        // TODO Start game loop in second thread. Register new AnimationTimer for Render Loop
        this.gameView.requestFocus();
        this.gameView.setOnKeyPressed((keyEvent) -> handleKeyEvent(keyEvent, true));
        this.gameView.setOnKeyReleased((keyEvent) -> handleKeyEvent(keyEvent, false));
    }

    private void handleKeyEvent(KeyEvent keyEvent, boolean state){
        String character = keyEvent.getCharacter().toUpperCase();
        System.out.println("key input: " + character);

        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        switch (character){
            case "W":
                controlInputManager.setForward(state);
                break;
            case "A":
                controlInputManager.setLeft(state);
                break;
            case "S":
                controlInputManager.setBackward(state);
                break;
            case "D":
                controlInputManager.setRight(state);
                break;
            case "ESC":
                controlInputManager.setPauseGame(state);
                break;
            default:
                break;
        }
    }
}
