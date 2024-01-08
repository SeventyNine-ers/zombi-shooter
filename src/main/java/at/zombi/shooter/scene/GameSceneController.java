package at.zombi.shooter.scene;

import at.zombi.shooter.game.GameRunManager;
import at.zombi.shooter.game.loop.GameMainLoop;
import at.zombi.shooter.game.rendering.GameRenderer;
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
        GameRunManager.getGameRunManager().startGame(gameView);
    }
}
