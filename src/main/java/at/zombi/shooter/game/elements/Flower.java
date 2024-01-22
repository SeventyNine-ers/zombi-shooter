package at.zombi.shooter.game.elements;

import at.zombi.shooter.game.util.Vector2D;
import at.zombi.shooter.scene.MainMenuController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Flower extends GameObject{
    public Flower(Vector2D position) {
        super(position);
    }

    @Override
    public List<Node> render() {
        Rectangle flowerModel = new Rectangle(getPosition().x - 10, getPosition().y - 10, 20, 20);

        flowerModel.setFill(new ImagePattern(new Image(
            String.valueOf(MainMenuController.class.getResource(
                "sprites/flower_sprite.png"
            )))));

        return List.of(flowerModel);
    }

    @Override
    public void update() {
        //do nothing
    }
}
