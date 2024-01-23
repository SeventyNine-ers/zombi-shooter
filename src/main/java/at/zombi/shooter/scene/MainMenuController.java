package at.zombi.shooter.scene;

import at.zombi.shooter.Application;
import at.zombi.shooter.game.util.PlayerData;
import at.zombi.shooter.manager.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
    private Button startButton;
    @FXML
    private Button howToButton;
    @FXML
    private Button scoreboardButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField playerNameTextField;


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

        // Falls schon eine Name gespeichert ist, gib ihn im Textfeld aus
        playerNameTextField.setText(PlayerData.getInstance().getPlayerName());
        // Speichere den String im Textfeld
        playerNameTextField.setOnAction(e -> {
            PlayerData.getInstance().setPlayerName(playerNameTextField.getText());
        });

        startButton.setOnAction(e -> {
            if(playerNameTextField.getText() != null) {
                PlayerData.getInstance().setPlayerName(playerNameTextField.getText());
            } else {
                PlayerData.getInstance().setPlayerName("No name entered");
            }
            try {
                onStartButtonClick();
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        howToButton.setOnAction(e -> {
            if(playerNameTextField.getText() != null) {
                PlayerData.getInstance().setPlayerName(playerNameTextField.getText());
            } else {
                PlayerData.getInstance().setPlayerName("No name entered");
            }
            try {
                onHowToButtonClick();
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        scoreboardButton.setOnAction(e -> {
            if(playerNameTextField.getText() != null) {
                PlayerData.getInstance().setPlayerName(playerNameTextField.getText());
            } else {
                PlayerData.getInstance().setPlayerName("No name entered");
            }
            try {
                onScoreboardButtonClick();
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        exitButton.setOnAction(e -> {
            onExitButtonClick();
        });
    }

    @FXML
    public void onStartButtonClick() throws IOException {
        SceneManager.getSceneManager().showGameScene();
    }

    @FXML
    public void onHowToButtonClick() throws IOException {
        SceneManager.getSceneManager().showHowTo();

    }

    @FXML
    public void onScoreboardButtonClick() throws IOException {
        SceneManager.getSceneManager().showScoreboard();
    }

    @FXML
    public void onExitButtonClick() {
        System.exit(1);
    }
}