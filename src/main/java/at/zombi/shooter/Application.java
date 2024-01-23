package at.zombi.shooter;

import at.zombi.shooter.manager.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class starts the application / the game
 * <p>
 * Author: Alexander Doubrava
 * Data: 06.01.2024
 */

public class Application extends javafx.application.Application {

    public static double SCREEN_WIDTH = 1280;
    public static double SCREEN_HEIGHT = 720;

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getSceneManager();

        Pane mainScene = sceneManager.getMainPane();
        Scene scene = new Scene(mainScene, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setTitle("Zombi-Shooter");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        sceneManager.showMainMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}