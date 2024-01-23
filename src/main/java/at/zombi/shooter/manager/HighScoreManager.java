package at.zombi.shooter.manager;

import at.zombi.shooter.game.util.HighscoreEntry;
import at.zombi.shooter.scene.MainMenuController;

import java.io.*;
import java.util.*;

/**
 * This class adds, saves and loads current highScore elements, which are stored inside a .dat file
 * for getting the current information for the Scoreboard
 * <p>
 * Author: Patrick Kristof
 * Date: 21.01.2024
 */

public class HighScoreManager {
    private static final String HIGHSCORE_FILE = String.valueOf(HighScoreManager.class.getResource(
        "scores/highscores.dat"
    ));

    public static List<HighscoreEntry> loadHighscores() {
        List<HighscoreEntry> highscores = new ArrayList<>();

        try(ObjectInputStream scoresInputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE))) {
            highscores = (List<HighscoreEntry>) scoresInputStream.readObject();
            //Fill a list with default values to show 10 entries at the scoreboard at all times
            while(highscores.size() < 10) {
                highscores.add(new HighscoreEntry("No Score", 0));
            }
        } catch(FileNotFoundException e) {
            //If the file does not exist the Highscores are set to default values
            while(highscores.size() < 10) {
                highscores.add(new HighscoreEntry("No Score", 0));
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return highscores;
    }

    //writes the current highscores inside the highscores.dat file
    public static void saveHighscores(List<HighscoreEntry> highscores) {
        try(ObjectOutputStream scoresOutputSteam = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE))) {
            scoresOutputSteam.writeObject(highscores);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //adds a highscore to the list Of HighScoreEntries
    public static void addHighscoreEntry(HighscoreEntry entry, List<HighscoreEntry> highscores) {
        highscores.add(entry);
        sortAndTrimHighscores(highscores);
        saveHighscores(highscores);
    }

    //sorts the Highscores --> only showing the TOP 10
    private static void sortAndTrimHighscores(List<HighscoreEntry> highscores) {
        Collections.sort(highscores, (e1, e2) -> Integer.compare(e2.getScore(), e1.getScore()));
        if(highscores.size() > 10) {
            highscores.subList(10, highscores.size()).clear();
        }
    }

    // Reset the highscores to their default values
    public static void resetHighscores() {
        List<HighscoreEntry> defaultHighscores = createDefaultHighscores();
        saveHighscores(defaultHighscores);
    }

    // creates a list of highscores with default values
    private static List<HighscoreEntry> createDefaultHighscores() {
        List<HighscoreEntry> defaultHighscores = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            defaultHighscores.add(new HighscoreEntry("No Score", 0));
        }
        return defaultHighscores;
    }
}
