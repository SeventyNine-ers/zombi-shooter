package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.Renderable;
import at.zombi.shooter.game.util.Vector2D;

public abstract class GameObject implements Renderable {

    private static long idCounter = 0;
    private long id;
    private Vector2D position;

    public GameObject(long x, long y) {
        this();
        this.position = new Vector2D(x, y);
    }

    public GameObject(Vector2D position) {
        this();
        this.position = new Vector2D(position);
    }

    private GameObject() {
        this.id = idCounter++;
    }

    // Run on every game tick
    public abstract void update();

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public long getId() {
        return id;
    }
}
