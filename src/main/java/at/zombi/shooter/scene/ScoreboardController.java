package at.zombi.shooter.scene;

import at.zombi.shooter.Application;
import at.zombi.shooter.game.util.HighscoreEntry;
import at.zombi.shooter.manager.HighScoreManager;
import at.zombi.shooter.manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import java.io.IOException;
import java.util.List;

public class ScoreboardController {
    @FXML
    private VBox ScoreboardVBox;
    @FXML
    private Text ScoreboardText;

    @FXML
    public void initialize() {
        List<HighscoreEntry> highScores = HighScoreManager.loadHighscores();
        String ScoreboardContent = "1. " + highScores.get(0).getPlayerName() + "  Score: " + highScores.get(0).getScore() + "\n\n" +
            "2. " + highScores.get(1).getPlayerName() + "  Score: " + highScores.get(1).getScore() + "\n\n" +
            "3. " + highScores.get(2).getPlayerName() + "  Score: " + highScores.get(2).getScore() + "\n\n" +
            "4. " + highScores.get(3).getPlayerName() + "  Score: " + highScores.get(3).getScore() + "\n\n" +
            "5. " + highScores.get(4).getPlayerName() + "  Score: " + highScores.get(4).getScore() + "\n\n" +
            "6. " + highScores.get(5).getPlayerName() + "  Score: " + highScores.get(5).getScore() + "\n\n" +
            "7. " + highScores.get(6).getPlayerName() + "  Score: " + highScores.get(6).getScore() + "\n\n" +
            "8. " + highScores.get(7).getPlayerName() + "  Score: " + highScores.get(7).getScore() + "\n\n" +
            "9. " + highScores.get(8).getPlayerName() + "  Score: " + highScores.get(8).getScore() + "\n\n" +
            "10. " + highScores.get(9).getPlayerName() + "  Score: " + highScores.get(9).getScore() + "\n\n";

        ScoreboardText.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.GREENYELLOW),
                new Stop(1, Color.DARKGREEN)
        );
        ScoreboardText.setFill(gradient);
        ScoreboardText.setTextAlignment(TextAlignment.LEFT);
        ScoreboardText.setText(ScoreboardContent);

        // Fuege Hintergundbild hinzu
        ScoreboardVBox.setBackground(new Background(new BackgroundImage(
                new Image(
                        String.valueOf(ScoreboardController.class.getResource(
                                "backgrounds/Zombi_Background_Scoreboard.jpg"
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

    public void onClearScoresButtonClick(ActionEvent actionEvent) throws IOException {
        HighScoreManager.resetHighscores();
        SceneManager.getSceneManager().showScoreboard();
    }
}
