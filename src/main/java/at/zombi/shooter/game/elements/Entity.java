package at.zombi.shooter.game.elements;


import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;

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
