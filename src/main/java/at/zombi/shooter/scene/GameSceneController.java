package at.zombi.shooter.scene;

import at.zombi.shooter.game.GameRunManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This class initializes the current gameView with a set background
 *
 * Author: Alexander Doubrava
 * Date: 06.01.2024
 */

public class GameSceneController {

    @FXML
    private Pane gameView;

    @FXML
    public void initialize() {
        gameView.setBackground(new Background(new BackgroundFill(Color.rgb(106, 190, 48), new CornerRadii(0), new Insets(0))));
        GameRunManager.getGameRunManager().startGame(gameView);
    }
}
