package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Spawner extends SolidGameObject{

    public Spawner(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-80, -80), new Vector2D(80, 80)));
    }

    @Override
    public List<Node> render() {
        Rectangle spawnerModel = new Rectangle(getPosition().x - 80, getPosition().y - 80, 160, 160);
        spawnerModel.setFill(Paint.valueOf("grey"));
        return List.of(spawnerModel);
    }

    @Override
    public void update() {
        //do nothing
    }
}
