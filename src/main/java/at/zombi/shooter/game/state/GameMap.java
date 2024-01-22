package at.zombi.shooter.game.state;

import at.zombi.shooter.game.elements.*;
import at.zombi.shooter.game.util.Vector2D;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Diese Klasse beinhaltet die Logik der GameMap, Zonen und Positionierung von Elementen
 * <p>
 * Ersteller: Alexander Doubrava
 * Datum: 06.01.2024
 */
public class GameMap {
    // TODO implement Zoning of the map so we don't check collisions with everything
    // is also interesting for the Camera Positioning and rendering of objects within this zone
    // bitwise zoning with e.g. xId, yId
    // HashMap after zoning -> collisions at the zone with all zones around the current zone
    private Player player;
    private Map<Long, GameObject> gameMap = new ConcurrentHashMap<Long, GameObject>();

    //TODO: Idee Maps über Textfiles laden --> einfaches Planning und Einbringen von verschiedenen Maps/Levels
    public void initMap() {

        for(int i = 0; i < 100; i++){
            add(new Flower(new Vector2D((Math.random() * 1560) + 100, (Math.random() * 1560) + 100)));
        }

        // TODO make a better map for game play
        setPlayer(new Player(
            new Vector2D(300, 300)
        ));

        add(new Spawner(new Vector2D(800, 800)));
        add(new Spawner(new Vector2D(1450, 500)));

        add(new Tree(new Vector2D(250, 300)));
        add(new Tree(new Vector2D(300, 220)));
        add(new Tree(new Vector2D(720, 400)));
        add(new Tree(new Vector2D(480, 400)));
        add(new Tree(new Vector2D(1000, 300)));
        add(new Tree(new Vector2D(800, 500)));
        add(new Tree(new Vector2D(850, 550)));
        add(new Tree(new Vector2D(750, 450)));
        add(new Tree(new Vector2D(700, 460)));
        add(new Tree(new Vector2D(1550, 1550)));

        add(new Zombie(new Vector2D(380, 280)));
        add(new Zombie(new Vector2D(380, 240)));
        add(new Zombie(new Vector2D(380, 260)));
        add(new Zombie(new Vector2D(380, 220)));

        for(int x = 800; x <= 1000; x += 40) {
            add(new Wall(new Vector2D(x, 400)));
        }

        for(int y = 400; y <= 720; y += 40) {
            add(new Wall(new Vector2D(1000, y)));
        }

        for(int x = 400; x <=1040; x += 40){
            add(new Wall(new Vector2D(x, 1300)));
            add(new Wall(new Vector2D(x, 1500)));
        }

        for(int y = 500; y <= 1300; y += 40){
            add(new Wall(new Vector2D(1041, y)));
            add(new Wall(new Vector2D(1241, y)));
        }

        for(int x = 0; x <= 1600; x += 40) {
            add(new Wall(new Vector2D(x, 0)));
            add(new Wall(new Vector2D(x, 1600)));
        }
        for(int y = 0; y <= 1600; y += 40) {
            add(new Wall(new Vector2D(0, y)));
            add(new Wall(new Vector2D(1600, y)));
        }

        add(new Wall(new Vector2D(200, 800)));
        add(new Wall(new Vector2D(300, 800)));
        add(new Wall(new Vector2D(200, 1320)));
        add(new Wall(new Vector2D(300, 1320)));

        for(int x = 80; x <= 200; x += 40){
            add(new Wall(new Vector2D(x, 841)));
            add(new Wall(new Vector2D(x, 1281)));
        }

        for(int x = 300; x <= 420; x += 40){
            add(new Wall(new Vector2D(x, 841)));
            add(new Wall(new Vector2D(x, 1281)));
        }

        for(int y = 840; y <= 1240; y += 40){
            add(new Wall(new Vector2D(80, y)));
            add(new Wall(new Vector2D(420, y)));
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
