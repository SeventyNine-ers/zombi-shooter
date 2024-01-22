package at.zombi.shooter.game.rendering;

import at.zombi.shooter.Application;
import at.zombi.shooter.game.Renderable;
import at.zombi.shooter.game.loop.DeltaTimeManager;
import at.zombi.shooter.game.state.GameState;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.manager.ControlInputManager;
import at.zombi.shooter.manager.SceneManager;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//Im Pairprogramming implementiert: Alexander Doubrava, Patrick Kristof
/**
 * Diese Klasse beinhaltet die Elemente die im Game-Overlay zu sehen sind und mit denen man agieren kann
 *
 * Ersteller: Alexander Doubrava, Patrick Kristof
 * Datum: 06.01.2024
 */
public class Overlay implements Renderable {

    private static final Rectangle TRANSPARENT_OVERLAY = new Rectangle(-1, -1, Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT);
    private long gameStartedAt;

    public Overlay() {
        this.gameStartedAt = 0;
        TRANSPARENT_OVERLAY.setFill(Paint.valueOf("#3e3e42a0"));
    }


    @Override
    public List<Node> render() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        if (GameState.RUNNING.equals(gameStateManager.getState())) {
            return gameHeadsUpDisplay();
        }
        if (GameState.PAUSED.equals(gameStateManager.getState())) {
            if (gameStateManager.isFirstStart()) {
                return firstStartScreen();
            }
            return Stream.of(gameHeadsUpDisplay(), pauseScreen())
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
        if (GameState.LOST.equals(gameStateManager.getState())) {
            return Stream.of(gameHeadsUpDisplay(), gameOverScreen("You are Dead!"))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
        if (GameState.WON.equals(gameStateManager.getState())) {
            return Stream.of(gameHeadsUpDisplay(), gameOverScreen(" You survived!"))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }

        return gameHeadsUpDisplay();
    }

    private List<Node>


    gameOverScreen(String text) {
        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        Text title = new Text(Application.SCREEN_WIDTH / 2 - 330, Application.SCREEN_HEIGHT / 2 - 150, text);
        title.setFont(new Font("Calibri", 120));
        title.setFill(Paint.valueOf("white"));

        Rectangle restartButton = new Rectangle(Application.SCREEN_WIDTH / 2 - 305, Application.SCREEN_HEIGHT / 2 - 8, 290, 70);
        restartButton.setFill(Paint.valueOf("green"));
        Text restartText = new Text(Application.SCREEN_WIDTH / 2 - 300, Application.SCREEN_HEIGHT / 2 + 50, "Restart");
        restartText.setFont(new Font("Calibri", 72));
        restartText.setFill(Paint.valueOf("white"));

        Rectangle menuButton = new Rectangle(Application.SCREEN_WIDTH / 2 + 15, Application.SCREEN_HEIGHT / 2 - 8, 340, 70);
        menuButton.setFill(Paint.valueOf("green"));
        Text menuText = new Text(Application.SCREEN_WIDTH / 2 + 15, Application.SCREEN_HEIGHT / 2 + 50, "Main Menu");
        menuText.setFont(new Font("Calibri", 72));
        menuText.setFill(Paint.valueOf("white"));

        Vector2D clickedAt = controlInputManager.hasLeftClicked();
        if (clickedAt != null) {
            Point2D clickedAtPoint = new Point2D(clickedAt.x, clickedAt.y);
            if (restartButton.contains(clickedAtPoint)) {
                gameStateManager.resetGameState();
                return List.of();
            }
            if (menuButton.contains(clickedAtPoint)) {
                try {
                    SceneManager.getSceneManager().showMainMenu();
                } catch (IOException exception) {
                }
            }
        }

        return List.of(TRANSPARENT_OVERLAY, title, restartButton, restartText, menuButton, menuText);
    }

    private List<Node> pauseScreen() {
        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        Text title = new Text(Application.SCREEN_WIDTH / 2 - 180, Application.SCREEN_HEIGHT / 2 - 150, "Paused");
        title.setFont(new Font("Calibri", 120));
        title.setFill(Paint.valueOf("white"));

        Rectangle resumeButton = new Rectangle(Application.SCREEN_WIDTH / 2 - 305, Application.SCREEN_HEIGHT / 2 - 8, 290, 70);
        resumeButton.setFill(Paint.valueOf("green"));
        Text resumeText = new Text(Application.SCREEN_WIDTH / 2 - 300, Application.SCREEN_HEIGHT / 2 + 50, "Continue");
        resumeText.setFont(new Font("Calibri", 72));
        resumeText.setFill(Paint.valueOf("white"));

        Rectangle menuButton = new Rectangle(Application.SCREEN_WIDTH / 2 + 15, Application.SCREEN_HEIGHT / 2 - 8, 340, 70);
        menuButton.setFill(Paint.valueOf("green"));
        Text menuText = new Text(Application.SCREEN_WIDTH / 2 + 15, Application.SCREEN_HEIGHT / 2 + 50, "Main Menu");
        menuText.setFont(new Font("Calibri", 72));
        menuText.setFill(Paint.valueOf("white"));

        Vector2D clickedAt = controlInputManager.hasLeftClicked();
        if (clickedAt != null) {
            Point2D clickedAtPoint = new Point2D(clickedAt.x, clickedAt.y);
            if (resumeButton.contains(clickedAtPoint)) {
                gameStateManager.setState(GameState.RUNNING);
                return List.of();
            }
            if (menuButton.contains(clickedAtPoint)) {
                try {
                    SceneManager.getSceneManager().showMainMenu();
                } catch (IOException exception) {
                }
            }
        }

        return List.of(TRANSPARENT_OVERLAY, title, resumeButton, resumeText, menuButton, menuText);
    }

    private List<Node> gameHeadsUpDisplay() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        List<Node> gameOverlay = new ArrayList<>();
        Text deltaTime = new Text(10, Application.SCREEN_HEIGHT - 10, "DeltaTime: " + DeltaTimeManager.getDeltaTimeManager().getDeltaTime());
        //gameOverlay.add(deltaTime);

        final long playerHealth = gameStateManager.getGameMap().getPlayer().getHealth();
        final long playerScore = gameStateManager.getGameMap().getPlayer().getScore();
        for (long i = 0; i < playerHealth; i += 100) {
            Rectangle health = new Rectangle(10 + (i / 2d), 10, 40, 40);
            health.setFill(Paint.valueOf("red"));
            gameOverlay.add(health);
        }

        Duration remainingPlaytime = Duration.ofSeconds(gameStateManager.getTimeRemaining());
        Text gameTimer = new Text(Application.SCREEN_WIDTH - 160, 50,
                String.format("%02d", remainingPlaytime.toMinutesPart())
                        + ":"
                        + String.format("%02d", remainingPlaytime.toSecondsPart())
        );
        gameTimer.setFont(new Font("Calibri", 64));
        gameOverlay.add(gameTimer);

        // Zeigt den Punktestand von der aktuelle Runden zentriert oben an.
        Text DisplayedPlayerScore = new Text(Application.SCREEN_WIDTH - 750, 50, "Score: " + playerScore);
        DisplayedPlayerScore.setFont(new Font("Calibri", 64));
        gameOverlay.add(DisplayedPlayerScore);
        return gameOverlay;
    }

    private List<Node> firstStartScreen() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        if (gameStartedAt == 0) {
            gameStartedAt = System.currentTimeMillis();
            return List.of(TRANSPARENT_OVERLAY);
        }

        final int startCountdown = 3;
        int startCounter = startCountdown - (int) ((System.currentTimeMillis() - gameStartedAt) / 1000);
        if (startCounter <= 0) {
            gameStartedAt = 0;
            gameStateManager.setFirstStart(false);
            gameStateManager.setState(GameState.RUNNING);
            return List.of(TRANSPARENT_OVERLAY);
        }

        Text counterText = new Text(Application.SCREEN_WIDTH / 2 - 50, Application.SCREEN_HEIGHT / 2 + 50, Integer.toString(startCounter));
        counterText.setFont(new Font("Calibri", 180));
        counterText.setFill(Paint.valueOf("white"));
        return List.of(TRANSPARENT_OVERLAY, counterText);
    }
}
