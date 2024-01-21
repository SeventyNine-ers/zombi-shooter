package at.zombi.shooter.game.state;

import at.zombi.shooter.game.elements.*;
import at.zombi.shooter.game.util.Vector2D;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private List<Integer> loopingDigits = new ArrayList<>();
    private List<String> elementsToAdd = new ArrayList<>();
    private List<String> elementToAdd = new ArrayList<>();
    private Player player;
    private Map<Long, GameObject> gameMap = new ConcurrentHashMap<Long, GameObject>();

    //TODO: Idee Maps über Textfiles laden --> einfaches Planning und Einbringen von verschiedenen Maps/Levels
    public void initMap() {

        getMapFromTextFile("src/main/resources/at/zombi/shooter/scene/maps/map_1.txt");
        // TODO make a better map for game play
        /*setPlayer(new Player(
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
        }*/

    }

    private void getMapFromTextFile(String textFileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(textFileName))) {

            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("loop:")) {
                    int indexOfLoop = line.indexOf(":");
                    String lineSubstring = removeWhiteSpacesFromString(line.substring(indexOfLoop + 1));
                    String[] loopAreas = lineSubstring.split(";");
                    //Elements are as followed:
                    // Element to looping with for-loop (first element, second element and addition to number +)
                    // Element to add to map, x-coordinates and y-coordinates
                    // more than one element can be added by the / tag


                    loopThroughMoreThanOneElement(loopAreas);

                    for(int i = this.loopingDigits.get(0); i <= this.loopingDigits.get(1); i += this.loopingDigits.get(2)) {
                        if(!this.elementsToAdd.isEmpty()) {
                            while(!this.elementsToAdd.isEmpty() && this.elementsToAdd.size() > 2) {
                                if(elementsToAdd.get(1).equals("x")) {
                                    addElementToMap(this.elementsToAdd.get(0), i, Integer.parseInt(this.elementsToAdd.get(2)));
                                } else if(elementsToAdd.get(2).equals("y")) {
                                    addElementToMap(this.elementsToAdd.get(0), Integer.parseInt(this.elementsToAdd.get(1)), i);
                                } else {
                                    addElementToMap(this.elementsToAdd.get(0), Integer.parseInt(this.elementsToAdd.get(1)), Integer.parseInt(this.elementsToAdd.get(2)));
                                }
                                this.elementsToAdd.subList(0, 3).clear();
                            }
                        }
                    }

                } else if(!Character.isDigit(line.charAt(0))) {
                    String lineSubstring = removeWhiteSpacesFromString(line);
                    String[] elementToAdd = lineSubstring.split(",");
                    addElementToMap(elementToAdd[0], Integer.parseInt(elementToAdd[1]), Integer.parseInt(elementToAdd[2]));
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loopThroughMoreThanOneElement(String[] loopAreas) {
        for(String loopArea : loopAreas) {
            if(Character.isDigit(loopArea.charAt(0))) {
                String[] loopingStrings = loopArea.split(",");
                for(String loopString : loopingStrings) {
                    this.loopingDigits.add(Integer.parseInt(loopString));
                }
            } else if(!Character.isDigit(loopArea.charAt(0)) && loopArea.contains("/")) {
                String[] loopingElements = loopArea.split("/");
                for(String element : loopingElements) {
                    this.elementsToAdd = (Stream.of(element.split(",")).map(String::new)
                        .collect(Collectors.toList()));
                }
            } else if(!Character.isDigit(loopArea.charAt(0))){
                this.elementToAdd = (Stream.of(loopArea.split(",")).map(String::new)
                    .collect(Collectors.toList()));
            }
        }
    }


    private String removeWhiteSpacesFromString(String stringToSplit) {
        return stringToSplit.trim().replaceAll(" ", "");
    }

    private void addElementToMap(String gameObject, int xPosition, int yPosition) {
        switch(gameObject) {
            case "Tree":
                add(new Tree(new Vector2D(xPosition, yPosition)));
                break;
            case "Wall":
                add(new Wall(new Vector2D(xPosition, yPosition)));
                break;
            case "Zombie":
                add(new Zombie(new Vector2D(xPosition, yPosition)));
                break;
            case "Spawner":
                add(new Spawner(new Vector2D(xPosition, yPosition)));
                break;
            case "Player":
                setPlayer(new Player(new Vector2D(xPosition, yPosition)));
                break;
            default:
                break;
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
