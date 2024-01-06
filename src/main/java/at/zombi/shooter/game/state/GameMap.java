package at.zombi.shooter.game.state;

import at.zombi.shooter.game.elements.GameObject;
import at.zombi.shooter.game.elements.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap {

    private Player player;
    private Map<Long, GameObject> gameMap = new ConcurrentHashMap<Long, GameObject>();


    public List<GameObject> getAllGameObjects() {
        ArrayList<GameObject> allObejcts = new ArrayList<>(gameMap.values());
        allObejcts.add(player);

        return Collections.unmodifiableList(allObejcts);
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
