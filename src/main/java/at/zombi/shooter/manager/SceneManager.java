package at.zombi.shooter.manager;

import at.zombi.shooter.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SceneManager {
    private static SceneManager sceneManager;
    private final Pane mainPane;

    private SceneManager() {
        this.mainPane = new Pane();
    }

    public static synchronized SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager();
        }
        return sceneManager;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public void showMainMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("main-menu.fxml"));
        getMainPane().getChildren().clear();
        getMainPane().getChildren().add(fxmlLoader.load());
    }

    public void showHowTo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HowToController.class.getResource("how-to.fxml"));
        getMainPane().getChildren().clear();
        getMainPane().getChildren().add(fxmlLoader.load());
    }

    public void showGameScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameSceneController.class.getResource("game-scene.fxml"));
        getMainPane().getChildren().clear();
        getMainPane().getChildren().add(fxmlLoader.load());
    }
}
