package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;

public abstract class SolidGameObject extends GameObject {

    private Hitbox hitbox;

    public SolidGameObject(Vector2D position, Hitbox hitbox) {
        super(position);
        this.hitbox = hitbox;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }
}
