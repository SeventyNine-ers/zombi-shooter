package at.zombi.shooter;

import at.zombi.shooter.manager.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getSceneManager();

        Pane mainScene = sceneManager.getMainPane();
        Scene scene = new Scene(mainScene, 1280, 720);
        stage.setTitle("Zombi-Shooter");
        stage.setScene(scene);
        stage.show();
        sceneManager.showMainMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}