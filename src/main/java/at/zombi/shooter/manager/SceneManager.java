package at.zombi.shooter.manager;

import at.zombi.shooter.game.GameRunManager;
import at.zombi.shooter.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * This class manages the scenes and when to show each one according to the game
 * <p>
 * Author: Alexander Doubrava
 * Date: 06.01.2024
 */

public class SceneManager {
    private static SceneManager sceneManager;
    private final AnchorPane mainPane;

    private SceneManager() {
        this.mainPane = new AnchorPane();
    }

    public static synchronized SceneManager getSceneManager() {
        if(sceneManager == null) {
            sceneManager = new SceneManager();
        }
        return sceneManager;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public void showMainMenu() throws IOException {
        GameRunManager.getGameRunManager().stopGame();
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("main-menu.fxml"));
        getMainPane().getChildren().clear();
        Node scene = fxmlLoader.load();
        getMainPane().getChildren().add(scene);
        anchorSceneAndAdjustSize(scene);
    }

    public void showHowTo() throws IOException {
        GameRunManager.getGameRunManager().stopGame();
        FXMLLoader fxmlLoader = new FXMLLoader(HowToController.class.getResource("how-to.fxml"));
        getMainPane().getChildren().clear();
        Node scene = fxmlLoader.load();
        getMainPane().getChildren().add(scene);
        anchorSceneAndAdjustSize(scene);
    }

    public void showScoreboard() throws IOException {
        GameRunManager.getGameRunManager().stopGame();
        FXMLLoader fxmlLoader = new FXMLLoader(HowToController.class.getResource("scoreboard.fxml"));
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

        if(this.mainPane != null && this.mainPane.getScene() != null) {
            this.mainPane.setMinWidth(this.mainPane.getScene().getWidth());
            this.mainPane.setMinHeight(this.mainPane.getScene().getHeight());
        }

        // Makes sure that mainPane child is stretched over the entire size
        AnchorPane.setTopAnchor(scene, 0.);
        AnchorPane.setBottomAnchor(scene, 0.);
        AnchorPane.setLeftAnchor(scene, 0.);
        AnchorPane.setRightAnchor(scene, 0.);
    }
}
