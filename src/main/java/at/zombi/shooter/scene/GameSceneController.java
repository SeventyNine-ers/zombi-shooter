package at.zombi.shooter.scene;

import at.zombi.shooter.Application;
import at.zombi.shooter.game.GameRunManager;
import at.zombi.shooter.game.loop.GameMainLoop;
import at.zombi.shooter.game.rendering.GameRenderer;
import at.zombi.shooter.manager.ControlInputManager;
import at.zombi.shooter.manager.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Collections;

public class GameSceneController {

    @FXML
    private Pane gameView;

    @FXML
    public void initialize() {
        //TODO: Background moves with player - not intended. Fix needed!
        /*gameView.setBackground(new Background(
            Collections.singletonList(new BackgroundFill(Color.rgb(106,190, 48),
                new CornerRadii(0),
                new Insets(0))),
            Collections.singletonList(new BackgroundImage(new Image(
            String.valueOf(MainMenuController.class.getResource(
                "sprites/area_sprite.gif"
            )),
            Application.SCREEN_WIDTH,
            Application.SCREEN_HEIGHT,
            false,
            true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        ))));*/
        gameView.setBackground(new Background(new BackgroundFill(Color.rgb(106,190,48), new CornerRadii(0), new Insets(0))));
        GameRunManager.getGameRunManager().startGame(gameView);
    }
}
