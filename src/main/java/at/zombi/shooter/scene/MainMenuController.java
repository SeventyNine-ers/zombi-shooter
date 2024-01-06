package at.zombi.shooter.scene;

import at.zombi.shooter.manager.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private VBox titleVBox;
    @FXML
    private Label gameTitle;

    @FXML
    public void initialize() {
        titleVBox.setBackground(
                new Background(new BackgroundFill(
                        Paint.valueOf("linear-gradient(rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%)"),
                        null,
                        null
                ))
        );
        gameTitle.setFont(new Font("Stencil", 64));
        gameTitle.setTextFill(Paint.valueOf("linear-gradient(from 25% 1% to 80% 100%, #3F8F45, #f77d36)"));
    }

    // TODO Remove this
    protected void oldCode() {
        // TODO Proof of concept only. This is pfusch to show scene switching works DON't EVER DO THIS!!
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                try {
                    SceneManager.getSceneManager().showGameScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }).start();
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