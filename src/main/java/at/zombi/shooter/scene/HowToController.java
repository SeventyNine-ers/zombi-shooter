package at.zombi.shooter.scene;

import at.zombi.shooter.Application;
import at.zombi.shooter.manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import java.io.IOException;

/**
 * This class contains the logic behind the HowTo-Button inside the game
 *
 * Authors: Alexander Doubrava, Patrick Kristof
 * Date: 06.01.2024
 */
public class HowToController {
    @FXML
    private VBox howToVBox;
    @FXML
    private Text howToText;

    @FXML
    public void initialize() {
        String howToContent = "Welcome to Zombi-Shooter!\n\n" +
                "Goal: Survive the 5 Minutes and get as many Points as possible!\n\n" +
                "Controls:\n" +
                "  - Movement: W(Forward)-A(Left)-S(Backwards)-D(Right)\n" +
                "  - Pausing the Game: Esc\n" +
                "  - Aiming: Moving the mouse\n" +
                "  - Shoot Bullets: Left-Click on Mouse\n\n" +
                "Ruleset:\n" +
                "  - You have 3 lives (on the left-upper corner)\n" +
                "  - Survive to win the game!\n\n" +
                "How the game calculates points:\n" +
                "  - Kill a Zombie (+10 Points)\n" +
                "  - Survive the time (+1 Point/Second)\n" +
                "  - Keep safe (+50 Points/life left)\n" +
                "  - Every bullet that doesn't hit costs you (-5 Points/bullet)\n\n" +
                "Happy Hunting!\n\n\n\n";

        howToText.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.GREENYELLOW),
            new Stop(1, Color.DARKGREEN)
        );
        howToText.setFill(gradient);
        howToText.setTextAlignment(TextAlignment.LEFT);
        howToText.setText(howToContent);

        // Fuege Hintergundbild hinzu
        howToVBox.setBackground(new Background(new BackgroundImage(
                new Image(
                        String.valueOf(HowToController.class.getResource(
                                "backgrounds/Zombi_Background_HowTo.jpg"
                        )),
                        Application.SCREEN_WIDTH,
                        Application.SCREEN_HEIGHT,
                        false,
                        true
                ),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));

    }

    public void onMenuButtonClick(ActionEvent actionEvent) throws IOException {
        SceneManager.getSceneManager().showMainMenu();
    }
}
