package at.zombi.shooter.scene;

import at.zombi.shooter.manager.ControlInputManager;
import at.zombi.shooter.manager.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameSceneController {

    @FXML
    private Pane gameView;

    @FXML
    public void initialize() {
        ControlInputManager.getControlInputManager();
        // TODO Start game loop in second thread. Register new AnimationTimer for Render Loop
    }
}
