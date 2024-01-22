package at.zombi.shooter.game.rendering;

import at.zombi.shooter.Application;
import at.zombi.shooter.game.elements.GameObject;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The camera is an object on the map and normally follows the player
 * The camera mainly contains logic to adjust the absolut position of objects on the map
 * to the relativ position of the camera view and logic to avoid drawing objects out of sight
 * *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */
public class Camera extends GameObject {

    public Camera(Vector2D position) {
        super(position);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Camera should not be part of GameMap and therefore not be ticked.");
    }

    @Override
    public List<Node> render() {
        var gameStateManager = GameStateManager.getGameStateManager();
        setPosition(new Vector2D(gameStateManager.getGameMap().getPlayer().getPosition()));

        double centerX = Application.SCREEN_WIDTH / 2;
        double centerY = Application.SCREEN_HEIGHT / 2;

        return gameStateManager.getGameMap().getAllGameObjects().stream()
                .flatMap(gmObj -> gmObj.render().stream())
                // TODO Refactor this to be less ugly....
                .filter(elem -> {
                    int maxGameObjSize = 150; // We don't expect an gameObject to be larger than this for simplicity reasons

                    if (elem instanceof Rectangle) {
                        Rectangle rect = (Rectangle) elem;

                        double newX = (rect.getX() - getPosition().x) + centerX;
                        double newY = (rect.getY() - getPosition().y) + centerY;

                        rect.setX(newX);
                        rect.setY(newY);

                        // Filter only those visible on screen
                        if (newX > Application.SCREEN_WIDTH + maxGameObjSize || newX + maxGameObjSize <= 0) {
                            return false;
                        }

                        if (newY > Application.SCREEN_HEIGHT + maxGameObjSize || newY + maxGameObjSize <= 0) {
                            return false;
                        }

                    //TODO: Line wird zurzeit nicht benutzt
                    } else if (elem instanceof Line) {
                        Line line = (Line) elem;

                        double newStartX = (line.getStartX() - getPosition().x) + centerX;
                        double newStartY = (line.getStartY() - getPosition().y) + centerY;
                        double newEndX = (line.getStartX() - getPosition().x) + centerX;
                        double newEndY = (line.getStartY() - getPosition().y) + centerY;

                        line.setStartY(newStartY);
                        line.setStartX(newStartX);
                        line.setEndX(newEndX);
                        line.setEndY(newEndY);

                        // Filter only those visible on screen
                        if ((newStartX > Application.SCREEN_WIDTH + maxGameObjSize || newStartX + maxGameObjSize <= 0) &&
                                (newEndX > Application.SCREEN_WIDTH + maxGameObjSize || newEndX + maxGameObjSize <= 0)
                        ) {
                            return false;
                        }

                        if ((newStartY > Application.SCREEN_HEIGHT + maxGameObjSize || newStartY + maxGameObjSize <= 0) &&
                                (newEndY > Application.SCREEN_HEIGHT + maxGameObjSize || newEndY + maxGameObjSize <= 0)
                        ) {
                            return false;
                        }
                    } else if (elem instanceof Polygon) {
                        Polygon line = (Polygon) elem;

                        for (int i = 0; i < line.getPoints().size(); i += 2) {
                            double xd = (line.getPoints().get(i) - getPosition().x) + centerX;
                            double yd = (line.getPoints().get(i + 1) - getPosition().y) + centerY;
                            line.getPoints().set(i, xd);
                            line.getPoints().set(i + 1, yd);
                        }

                        // Filter only those visible on screen
                        for (int i = 0; i < line.getPoints().size(); i += 2) {
                            double x = line.getPoints().get(i);
                            double y = line.getPoints().get(i + 1);
                            if (x <= Application.SCREEN_WIDTH && x >= 0 && y <= Application.SCREEN_HEIGHT && y >= 0) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        //TODO: Instance of Text, z.B. Tree Text // Tree hat Rectangle und Text
                        // Fallback mit Reflection - für Elemente die vorher nicht benutzt wurden
                        try {
                            var setX = elem.getClass().getMethod("setX", double.class);
                            var getX = elem.getClass().getMethod("getX");
                            var setY = elem.getClass().getMethod("setY", double.class);
                            var getY = elem.getClass().getMethod("getY");

                            double newX = ((Double) getX.invoke(elem) - getPosition().x) + centerX;
                            double newY = ((Double) getY.invoke(elem) - getPosition().y) + centerY;

                            setX.invoke(elem, newX);
                            setY.invoke(elem, newY);

                            //TODO: Refactoring, außerhalb des Screens wird nicht gerendet, spart Performance
                            // Filter only those visible on screen
                            if (newX > Application.SCREEN_WIDTH + maxGameObjSize || newX + maxGameObjSize <= 0) {
                                return false;
                            }

                            if (newY > Application.SCREEN_HEIGHT + maxGameObjSize || newY + maxGameObjSize <= 0) {
                                return false;
                            }
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {}
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
