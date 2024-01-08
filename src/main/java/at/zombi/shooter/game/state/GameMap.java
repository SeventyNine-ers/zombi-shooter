package at.zombi.shooter.game.state;

import at.zombi.shooter.game.elements.*;
import at.zombi.shooter.game.util.Hitbox;
import at.zombi.shooter.game.util.Vector2D;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GameMap {

    private Player player;
    private Map<Long, GameObject> gameMap = new ConcurrentHashMap<Long, GameObject>();


    public void initMap() {
        setPlayer(new Player(
                new Vector2D(300, 300)
        ));

        add(new Tree(new Vector2D(250, 300)));
        add(new Tree(new Vector2D(300, 220)));
        add(new Zombie(new Vector2D(340, 280)));
        add(new Zombie(new Vector2D(340, 280)));
        add(new Zombie(new Vector2D(340, 280)));
        add(new Zombie(new Vector2D(340, 220)));

        for (int x = 0; x <= 800; x += 40) {
            add(new Wall(new Vector2D(x, 0)));
            add(new Wall(new Vector2D(x, 800)));
        }
        for (int y = 0; y <= 800; y += 40) {
            add(new Wall(new Vector2D(0, y)));
            add(new Wall(new Vector2D(800, y)));
        }

    }

    public List<GameObject> getAllGameObjects() {
        ArrayList<GameObject> allObejcts = new ArrayList<>(gameMap.values());
        allObejcts.add(player);

        return Collections.unmodifiableList(allObejcts);
    }

    public List<SolidGameObject> getCollidableGameObjects() {
        ArrayList<GameObject> allObejcts = new ArrayList<>(gameMap.values());
        allObejcts.add(player);

        return allObejcts.stream()
                .filter(el -> el instanceof SolidGameObject)
                .map(el -> (SolidGameObject) el)
                .collect(Collectors.toUnmodifiableList());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void add(GameObject gameObject) {
        this.gameMap.put(gameObject.getId(), gameObject);
    }

    public void remove(GameObject gameObject) {
        this.gameMap.remove(gameObject.getId());
    }
}
