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

    private final int attackDamage;

    public Zombie(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-20, -20), new Vector2D(20, 20)), 100);
        attackDamage = 100;
    }

    @Override
    public void update() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        double deltaTime = DeltaTimeManager.getDeltaTimeManager().getDeltaTime();
        if (getHealth() <= 0) {
            gameStateManager.getGameMap().remove(this);
            return;
        }

        // TODO Make an actual enemy AI
        getVelocity().add(new Vector2D(0.5 - Math.random(), 0.5 - Math.random()).getMultiplied(0.2));
        getVelocity().normalize();
        getVelocity().multiply(2.5 * deltaTime);

        processCollisionAndApplyMovement();
    }

    private void processCollisionAndApplyMovement() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        for (SolidGameObject solid : gameStateManager.getGameMap().getCollidableGameObjects()) {
            if (solid instanceof Bullet || solid instanceof Zombie) {
                continue;
            }

            boolean overlapXCords = solid.getAbsolutHitbox().overlap(
                    getHitbox().getAbsolutHitBox(getPosition().getAdded(new Vector2D(getVelocity().x, 0)))
            );
            boolean overlapYCords = solid.getAbsolutHitbox().overlap(
                    getHitbox().getAbsolutHitBox(getPosition().getAdded(new Vector2D(0, getVelocity().y)))
            );

            if (overlapXCords && overlapYCords) {
                return;
            }
            if (overlapXCords) {
                setVelocity(new Vector2D(0, getVelocity().y));
            }
            if (overlapYCords) {
                setVelocity(new Vector2D(getVelocity().x, 0));
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

    public int getAttackDamage() {
        return attackDamage;
    }
}
