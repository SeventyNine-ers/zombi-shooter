package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.scene.MainMenuController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * This class contains the logic for the GameObject Spawner (Enemyspawner)
 * <p>
 * Author: Julia Sass
 * Date: 21.01.2024
 */

public class Spawner extends SolidGameObject {
    private static final ImagePattern SPAWNER_SPRITE = new ImagePattern(new Image(
        String.valueOf(MainMenuController.class.getResource(
            "sprites/spawner_sprite.gif"
        ))));

    public Spawner(Vector2D position) {
        super(position, new Hitbox(new Vector2D(-80, -80), new Vector2D(80, 80)));
    }

    @Override
    public List<Node> render() {
        Rectangle spawnerModel = new Rectangle(getPosition().x - 80, getPosition().y - 80, 160, 160);
        spawnerModel.setFill(SPAWNER_SPRITE);
        return List.of(spawnerModel);
    }

    @Override
    public void update() {
        //do nothing
    }
}
