package at.zombi.shooter.game.rendering;

import at.zombi.shooter.game.Renderable;
import at.zombi.shooter.game.loop.DeltaTimeManager;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.List;

public class Overlay implements Renderable {
    @Override
    public List<Node> render() {
        return List.of(new Text(10, 10, "DeltaTime: " + DeltaTimeManager.getDeltaTimeManager().getDeltaTime()));
    }
}
