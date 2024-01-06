package at.zombi.shooter.game.util;

// For now our hitbox is only Rectangular
public class Hitbox {

    private Vector2D topLeft;
    private Vector2D bottomRight;

    public Hitbox(Vector2D topLeft, Vector2D bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
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
}
