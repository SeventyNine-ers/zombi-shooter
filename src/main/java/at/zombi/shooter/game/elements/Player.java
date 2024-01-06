package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;

import java.util.List;

public class Player extends Entity {

    public Player(Vector2D position, Hitbox hitbox, long health) {
        super(position, hitbox, health);
    }

    @Override
    public void update() {

    }

    @Override
    public List<Node> render() {
        return null;
    }

}
