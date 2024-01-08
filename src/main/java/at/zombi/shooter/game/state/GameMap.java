package at.zombi.shooter.game.state;

import at.zombi.shooter.game.elements.*;
import at.zombi.shooter.game.util.Vector2D;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GameMap {
    // TODO implement Zoning of the map so we don't check collisions with everything
    private Player player;
    private Map<Long, GameObject> gameMap = new ConcurrentHashMap<Long, GameObject>();


    public void initMap() {
        // TODO make a better map for game play
        setPlayer(new Player(
                new Vector2D(300, 300)
        ));

        add(new Tree(new Vector2D(250, 300)));
        add(new Tree(new Vector2D(300, 220)));
        add(new Zombie(new Vector2D(380, 280)));
        add(new Zombie(new Vector2D(380, 240)));
        add(new Zombie(new Vector2D(380, 260)));
        add(new Zombie(new Vector2D(380, 220)));

        for (int x = 0; x <= 1600; x += 40) {
            add(new Wall(new Vector2D(x, 0)));
            add(new Wall(new Vector2D(x, 1600)));
        }
        for (int y = 0; y <= 1600; y += 40) {
            add(new Wall(new Vector2D(0, y)));
            add(new Wall(new Vector2D(1600, y)));
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
