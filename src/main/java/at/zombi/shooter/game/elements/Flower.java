package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.scene.MainMenuController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * This class contains the logic of the gameObject Flower
 *
 * Author: Julia Sass
 * Date: 22.01.2024
 */

public class Flower extends GameObject{

    private static final ImagePattern FLOWER_SPRITE = new ImagePattern(new Image(
        String.valueOf(MainMenuController.class.getResource(
        "sprites/flower_sprite.png"
    ))));
    public Flower(Vector2D position) {
        super(position);
    }

    @Override
    public List<Node> render() {
        Rectangle flowerModel = new Rectangle(getPosition().x - 10, getPosition().y - 10, 20, 20);

        flowerModel.setFill(FLOWER_SPRITE);

        return List.of(flowerModel);
    }

    @Override
    public void update() {
        //do nothing
    }
}
