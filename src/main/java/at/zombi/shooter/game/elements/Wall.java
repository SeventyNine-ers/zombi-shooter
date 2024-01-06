package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class Wall extends SolidGameObject {

    public Wall(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-21, -21), new Vector2D(21, 21)));
    }

    @Override
    public void update() {

    }

    @Override
    public List<Node> render() {
        Rectangle wallModel = new Rectangle(getPosition().x - 20, getPosition().y - 20, 40, 40);
        wallModel.setFill(Paint.valueOf("grey"));
        return List.of(wallModel);
    }
}
