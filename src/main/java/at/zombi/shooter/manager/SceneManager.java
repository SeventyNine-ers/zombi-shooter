package at.zombi.shooter.manager;

import at.zombi.shooter.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SceneManager {
    private static SceneManager sceneManager;
    private final AnchorPane mainPane;

    private SceneManager() {
        this.mainPane = new AnchorPane();
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
        Node scene = fxmlLoader.load();
        getMainPane().getChildren().add(scene);
        anchorSceneAndAdjustSize(scene);
    }

    public void showHowTo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HowToController.class.getResource("how-to.fxml"));
        getMainPane().getChildren().clear();
        Node scene = fxmlLoader.load();
        getMainPane().getChildren().add(scene);
        anchorSceneAndAdjustSize(scene);
    }

    public void showGameScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameSceneController.class.getResource("game-scene.fxml"));
        getMainPane().getChildren().clear();
        Node scene = fxmlLoader.load();
        getMainPane().getChildren().add(scene);
        anchorSceneAndAdjustSize(scene);
    }

    private void anchorSceneAndAdjustSize(Node scene) {
        // Adjust size of mainPane if possible
        try {
            this.mainPane.setMinWidth(this.mainPane.getScene().getWidth());
            this.mainPane.setMinHeight(this.mainPane.getScene().getHeight());
        } catch (NullPointerException exception) {
            // The stage isn't created yet. So we ignore resizing.
        }

        // Makes sure that mainPane child is stretched over the entire size
        AnchorPane.setTopAnchor(scene, 0.);
        AnchorPane.setBottomAnchor(scene, 0.);
        AnchorPane.setLeftAnchor(scene, 0.);
        AnchorPane.setRightAnchor(scene, 0.);
    }
}
