package at.zombi.shooter.game.elements;

import at.zombi.shooter.Application;
import at.zombi.shooter.game.loop.DeltaTimeManager;
import at.zombi.shooter.game.state.GameState;
import at.zombi.shooter.game.state.GameStateManager;
import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.manager.ControlInputManager;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

import java.util.List;

public class Player extends Entity {

    private long lastZombieHit = 0;

    public Player(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-20, -20), new Vector2D(20, 20)), 300);
    }

    @Override
    public void update() {
        handleShot();
        setVelocity(getMovementVector());

        processCollisionAndApplyMovement();

        if (getHealth() <= 0) {
            GameStateManager.getGameStateManager().setState(GameState.LOST);
        }
    }

    private void handleShot() {
        Vector2D target = ControlInputManager.getControlInputManager().hasLeftClicked();
        if (target == null) {
            return;
        }

        target.add(-Application.SCREEN_WIDTH / 2, -Application.SCREEN_HEIGHT / 2);
        target.normalize();

        int speed = 6;
        Bullet bullet = new Bullet(getPosition(), target.getMultiplied(speed));
        GameStateManager.getGameStateManager().getGameMap().add(bullet);
    }

    private void handleEntityCollision(Entity entity) {
        final int damageImmunitySec = 3;

        // Reduce health if we touch zombie but only every 3 sec
        if (entity instanceof Zombie && System.currentTimeMillis() - lastZombieHit > damageImmunitySec * 1000) {
            lastZombieHit = System.currentTimeMillis();
            setHealth(getHealth() - ((Zombie) entity).getAttackDamage());
        }
    }

    private Vector2D getMovementVector() {
        ControlInputManager controlInputManager = ControlInputManager.getControlInputManager();
        double deltaTime = DeltaTimeManager.getDeltaTimeManager().getDeltaTime();

        int speed = 4;
        Vector2D velo = new Vector2D(0, 0);
        if (controlInputManager.isForward()) {
            velo.add(0, -1);
        }
        if (controlInputManager.isBackward()) {
            velo.add(0, 1);
        }
        if (controlInputManager.isLeft()) {
            velo.add(-1, 0);
        }
        if (controlInputManager.isRight()) {
            velo.add(1, 0);
        }
        velo.normalize();
        velo.multiply(speed * deltaTime);

        return velo;
    }

    private void processCollisionAndApplyMovement() {
        GameStateManager gameStateManager = GameStateManager.getGameStateManager();

        for (SolidGameObject solid : gameStateManager.getGameMap().getCollidableGameObjects()) {
            if (solid == this || solid instanceof Bullet) {
                continue;
            }

            boolean overlapXCords = solid.getAbsolutHitbox().overlap(
                    getHitbox().getAbsolutHitBox(getPosition().getAdded(new Vector2D(getVelocity().x, 0)))
            );
            boolean overlapYCords = solid.getAbsolutHitbox().overlap(
                    getHitbox().getAbsolutHitBox(getPosition().getAdded(new Vector2D(0, getVelocity().y)))
            );

            if ((overlapXCords || overlapYCords) && solid instanceof Entity) {
                handleEntityCollision((Entity) solid);
            }
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

        // Let player blink after zombie hit for 5 sek
        if (System.currentTimeMillis() - lastZombieHit < 5000 && (System.currentTimeMillis() / 100) * 100 % 200 == 0) {
            playerModel.setFill(Paint.valueOf("grey"));
        } else {
            playerModel.setFill(Paint.valueOf("orange"));
        }
        /* TODO Make player a triangle that points at the mouse
            Polygon playerModel = new Polygon(getPosition().x, getPosition().y - 20, getPosition().x + 20, getPosition().y + 20, getPosition().x - 20, getPosition().y + 20);
            playerModel.setFill(Paint.valueOf("orange"));
        */
        return List.of(playerModel);
    }

}
