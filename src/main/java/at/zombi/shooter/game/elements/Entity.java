package at.zombi.shooter.game.elements;


import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;

public abstract class Entity extends SolidGameObject {

    private long health;

    public Entity(Vector2D position, Hitbox hitbox, long health) {
        super(position, hitbox);
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }
}
