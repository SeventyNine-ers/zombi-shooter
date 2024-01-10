package at.zombi.shooter.scene;

import at.zombi.shooter.Application;
import at.zombi.shooter.manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private VBox titleVBox;
    @FXML
    private Label gameTitle;

    @FXML
    public void initialize() {

        // Schriftart und Groesse
        gameTitle.setFont(Font.font("Chiller", FontWeight.BOLD, 120));
        // Dunkelroter Text
        gameTitle.setTextFill(Paint.valueOf("#B22222"));

        // Fuege Hintergundbild hinzu
        titleVBox.setBackground(new Background(new BackgroundImage(
                new Image(
                        String.valueOf(MainMenuController.class.getResource(
                                "backgrounds/Zombi_Background_Titlescreen.png"
                        )),
                        Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT,
                        false,
                        true
                ),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));

    }

    @FXML
    public void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        SceneManager.getSceneManager().showGameScene();
    }

    @FXML
    public void onHowToButtonClick(ActionEvent actionEvent) throws IOException {
        SceneManager.getSceneManager().showHowTo();
    }

    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(1);
    }
}