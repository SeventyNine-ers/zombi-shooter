package at.zombi.shooter.game.elements;


import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;

/**
 * This class contains the abstract class Entity which extends SolidGameObject
 * Therein are elements like e.g. Healthpoints, velocity of an entity in form of an object
 * - information for positioning with Vector2D(another class)
 * <p>
 * Author: Alexander Doubrava
 * Date: 06.01.2024
 */

public abstract class Entity extends SolidGameObject {

    private long health;
    private Vector2D velocity = new Vector2D(0, 0);

    public Entity(Vector2D position, Hitbox hitbox, long health) {
        super(position, hitbox);
        this.health = health;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
