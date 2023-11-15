package at.zombi.shooter.scene;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HowToController {
    @FXML
    private Label howToText;

    @FXML
    public void initialize() {
        howToText.setText("How-To Screen!");
    }
}