package at.zombi.shooter.game.rendering;

import at.zombi.shooter.game.elements.GameObject;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;

import java.util.List;

/*
 * The camera is an object on the map and normally follows the player
 * The camera mainly contains logic to adjust the absolut position of objects on the map
 * to the relativ position of the camera view and logic to avoid drawing objects out of sight
 */
public class Camera extends GameObject {

    public Camera(Vector2D position) {
        super(position);
    }

    @Override
    public void update() {

    }

    @Override
    public List<Node> render() {
        return null;
    }
}
