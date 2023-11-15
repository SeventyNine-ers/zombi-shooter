package at.zombi.shooter.scene;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GameSceneController {

    @FXML
    private Pane gameView;

    @FXML
    public void initialize() {
        // TODO Start game loop in second thread. Register new AnimationTimer for Render Loop
    }
}
