package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.loop.DeltaTimeManager;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Bullet extends Entity {

    public Bullet(Vector2D position, Vector2D velocity) {
        super(position, new Hitbox(new Vector2D(-5, -5), new Vector2D(5, 5)), 100);
        setVelocity(new Vector2D(velocity));
    }

    @Override
    public void update() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        double deltaTime = DeltaTimeManager.getDeltaTimeManager().getDeltaTime();
        getPosition().add(getVelocity().x * deltaTime, getVelocity().y * deltaTime);

        for (SolidGameObject solid : gameStateManager.getGameMap().getCollidableGameObjects()) {
            if (solid == this || solid instanceof Player) {
                continue;
            }
            boolean overlap = solid.getAbsolutHitbox().overlap(getAbsolutHitbox());
            if (overlap) {
                if (solid instanceof Entity) {
                    Entity entity = ((Entity)solid);
                    entity.setHealth(entity.getHealth() - getHealth());
                }
                gameStateManager.getGameMap().remove(this);
                return;
            }
        }
    }

    @Override
    public List<Node> render() {
        Rectangle shot = new Rectangle(getPosition().x - 10, getPosition().y - 5, 20, 10);
        shot.setRotate(getVelocity().getAngle() * (180 / 3.1415926));
        shot.setFill(Paint.valueOf("blue"));
        return List.of(shot);
    }
}
