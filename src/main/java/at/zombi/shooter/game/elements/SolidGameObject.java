package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;

/**
 * Diese Klasse beinhaltet die Beschreibung der Game-Objekte welche als Kollisionsgegenstände im Spiel agieren.
 * Beispiele: Kugel, Baum, Wand --> Elemente die nicht "übergangen" werden sollen
 * <p>
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */

public abstract class SolidGameObject extends GameObject {

    private Hitbox hitbox;

    public SolidGameObject(Vector2D position, Hitbox hitbox) {
        super(position);
        this.hitbox = hitbox;
    }

    // Gives you the hitbox relative to the GameObject Coords
    public Hitbox getHitbox() {
        return hitbox;
    }

    // Gives you the hitbox in absolut coordinates
    public Hitbox getAbsolutHitbox() {
        return hitbox.getAbsolutHitBox(getPosition());
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }
}
