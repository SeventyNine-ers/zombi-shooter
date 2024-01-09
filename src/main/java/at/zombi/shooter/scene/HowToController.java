package at.zombi.shooter.scene;

import at.zombi.shooter.Application;
import at.zombi.shooter.manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import java.io.IOException;

public class HowToController {
    @FXML
    private VBox howToVBox;
    @FXML
    private Text howToText;

    @FXML
    public void initialize() {
        String howToContent = "Willkommen zum Zombie Shooter!\n\n" +
                "Ziel: Überlebe 5 Minuten und besiege die Zombies!\n\n" +
                "Steuerung:\n" +
                "  - Bewegung: W-A-S-D\n" +
                "  - Pause: Esc\n" +
                "  - Zielen: Maus bewegen\n" +
                "  - Schießen: Linksklick\n\n" +
                "Spielregeln:\n" +
                "  - Du hast 3 Leben (oben links)\n" +
                "  - Überlebe, um das Spiel zu gewinnen!\n\n" +
                "Viel Erfolg und gute Jagd!\n\n\n\n";

        howToText.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.BLACK),
                new Stop(1, Color.DARKGRAY)
        );
        howToText.setFill(gradient);
        howToText.setTextAlignment(TextAlignment.LEFT);
        howToText.setText(howToContent);

        // Füge Hintergundbild hinzu
        howToVBox.setBackground(new Background(new BackgroundImage(new Image("/backgrounds/Zombi_Background_HowTo.jpg", Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

    }

    public void onMenuButtonClick(ActionEvent actionEvent) throws IOException {
        SceneManager.getSceneManager().showMainMenu();
    }
}
