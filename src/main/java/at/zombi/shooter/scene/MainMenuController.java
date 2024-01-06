package at.zombi.shooter.scene;

import at.zombi.shooter.manager.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");


        // TODO Proof of concept only. This is pfusch to show scene switching works DON't EVER DO THIS!!
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {
                try {
                    SceneManager.getSceneManager().showGameScene();
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }).start();
    }
}