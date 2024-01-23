package at.zombi.shooter.game;


import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.util.List;

/**
 * Interface for the GameObjects which are currently rendered
 * <p>
 * Author: Alexander Doubrava
 * Date: 06.01.2024
 */
public interface Renderable {

    List<Node> render();
}
