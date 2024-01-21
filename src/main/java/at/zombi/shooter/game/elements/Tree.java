package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Diese Klasse beinhaltet die Logik in Bezug auf das Game-Element Baum (Tree)
 *
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */

public class Tree extends SolidGameObject {

    public Tree(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-21, -21), new Vector2D(21, 21)));
    }

    @Override
    public void update() {

    }

    @Override
    public List<Node> render() {
        Rectangle treeModel = new Rectangle(getPosition().x - 20, getPosition().y - 20, 40, 40);
        treeModel.setFill(Paint.valueOf("brown"));
        Text text = new Text(getPosition().x - 15, getPosition().y -5, "Tree");
        text.setFill(Paint.valueOf("green"));
        text.setFont(new Font(16));
        return List.of(treeModel, text);
    }
}
