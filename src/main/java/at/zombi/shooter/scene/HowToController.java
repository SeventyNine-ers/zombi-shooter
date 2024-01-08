package at.zombi.shooter.scene;

import at.zombi.shooter.manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HowToController {
    @FXML
    private Label howToText;

    @FXML
    public void initialize() {
        howToText.setText("Movement: W-A-S-D, Pause: Esc, Shoot: LeftClick");
    }

    public void onMenuButtonClick(ActionEvent actionEvent) throws IOException {
        SceneManager.getSceneManager().showMainMenu();
    }
}