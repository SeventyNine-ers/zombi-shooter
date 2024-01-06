package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.loop.DeltaTimeManager;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Zombie extends Entity {

    public Zombie(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-20, -20), new Vector2D(20, 20)), 100);
    }

    @Override
    public void update() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        double deltaTime = DeltaTimeManager.getDeltaTimeManager().getDeltaTime();
        if (getHealth() <= 0) {
            gameStateManager.getGameMap().remove(this);
            return;
        }

        getVelocity().add(new Vector2D(0.5 - Math.random(), 0.5 - Math.random()).getMultiplied(0.3));
        getVelocity().normalize();
        getVelocity().multiply(2.5 * deltaTime);
        for (SolidGameObject solid : gameStateManager.getGameMap().getCollidableGameObjects()) {
            if (solid == this || solid instanceof Bullet || solid instanceof Zombie) {
                continue;
            }
            boolean overlap = solid.getAbsolutHitbox().overlap(getHitbox().getAbsolutHitBox(getPosition().getAdded(getVelocity())));
            if (overlap) {
                return;
            }
        }
        getPosition().add(getVelocity());
    }

    @Override
    public List<Node> render() {
        Rectangle playerModel = new Rectangle(getPosition().x - 20, getPosition().y - 20, 40, 40);
        playerModel.setFill(Paint.valueOf("green"));
        return List.of(playerModel);
    }
}
