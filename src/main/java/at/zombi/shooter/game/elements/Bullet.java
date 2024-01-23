package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.loop.DeltaTimeManager;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.scene.MainMenuController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * Diese Klasse beinhaltet die Logik in Bezug auf das Game-Element Kugel (eines Gewehrs/Pistole)
 * <p>
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */

public class Bullet extends Entity {

    private static final ImagePattern BULLET_SPRITE = new ImagePattern(new Image(
        String.valueOf(MainMenuController.class.getResource(
            "sprites/bullet_sprite.png"
        ))));
    private final int attackDamage;

    public Bullet(Vector2D position, Vector2D velocity) {
        super(position, new Hitbox(new Vector2D(-5, -5), new Vector2D(5, 5)), 100);
        setVelocity(new Vector2D(velocity));
        attackDamage = 100;
    }

    @Override
    public void update() {
        double deltaTime = DeltaTimeManager.getDeltaTimeManager().getDeltaTime();
        getPosition().add(getVelocity().x * deltaTime, getVelocity().y * deltaTime);

        processCollisions();
    }

    private void processCollisions() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        for(SolidGameObject solid : gameStateManager.getGameMap().getCollidableGameObjects()) {
            if(solid instanceof Bullet || solid instanceof Player) {
                continue;
            }
            if(solid.getAbsolutHitbox().overlap(getAbsolutHitbox())) {
                handleCollisionEvent(solid);
            }
        }
    }

    private void handleCollisionEvent(SolidGameObject solid) {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();
        Player player = gameStateManager.getGameMap().getPlayer();
        // Deal damage to entity
        if(solid instanceof Entity) {
            Entity entity = ((Entity) solid);
            entity.setHealth(entity.getHealth() - attackDamage);
        } else {
            // Zieht 5 Punkte ab, wenn man keinen Gegner trifft
            player.updateMissScore();
        }

        // Bullet de-spawns after collision
        gameStateManager.getGameMap().remove(this);
    }

    @Override
    public List<Node> render() {
        Rectangle shot = new Rectangle(getPosition().x - 10, getPosition().y - 5, 20, 10);
        final double radToDeg = (180 / 3.1415926);
        shot.setRotate(getVelocity().getAngle() * radToDeg);
        shot.setFill(BULLET_SPRITE);

        return List.of(shot);
    }
}
