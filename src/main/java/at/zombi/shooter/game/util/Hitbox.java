package at.zombi.shooter.game.util;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

// For now our hitbox is only Rectangular
public class Hitbox {

    private Vector2D topLeft;
    private Vector2D bottomRight;

    public Hitbox(Vector2D topLeft, Vector2D bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    // Gives you the hitbox in absolut coordinates
    public Hitbox getAbsolutHitBox(Vector2D position) {
        return new Hitbox(topLeft.getAdded(position), bottomRight.getAdded(position));
    }

    public Vector2D getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Vector2D topLeft) {
        this.topLeft = topLeft;
    }

    public Vector2D getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Vector2D bottomRight) {
        this.bottomRight = bottomRight;
    }

    // Only usefull on absolut hitboxes.
    public boolean overlap(Hitbox hitbox) {
        Bounds here = new BoundingBox(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
        Bounds notHere = new BoundingBox(hitbox.topLeft.x, hitbox.topLeft.y, hitbox.bottomRight.x - hitbox.topLeft.x,
                hitbox.bottomRight.y - hitbox.topLeft.y);

        return here.intersects(notHere);
    }

    @Override
    public String toString() {
        return "Hitbox{" +
                "topLeft=" + topLeft +
                ", bottomRight=" + bottomRight +
                '}';
    }
}
