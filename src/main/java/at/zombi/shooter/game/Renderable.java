package at.zombi.shooter.game;


import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.util.List;

/**
 * Interface für Objekte die in Kameraansicht gerendert werden
 * *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */
public interface Renderable {

    List<Node> render();
}
