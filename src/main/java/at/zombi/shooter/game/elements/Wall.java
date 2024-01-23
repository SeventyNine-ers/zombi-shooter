package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.scene.MainMenuController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Diese Klasse beinhaltet die Logik in Bezug auf das Game-Element Wand (Wall)
 * <p>
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */
public class Wall extends SolidGameObject {

    private static final ImagePattern WALL_SPRITE = new ImagePattern(new Image(
        String.valueOf(MainMenuController.class.getResource(
            "sprites/wall_sprite.png"
        ))));

    public Wall(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-21, -21), new Vector2D(21, 21)));
    }

    @Override
    public void update() {

    }

    @Override
    public List<Node> render() {
        Rectangle wallModel = new Rectangle(getPosition().x - 20, getPosition().y - 20, 40, 40);
        wallModel.setFill(WALL_SPRITE);

        return List.of(wallModel);
    }
}
