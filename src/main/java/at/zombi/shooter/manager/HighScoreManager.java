package at.zombi.shooter.manager;

import at.zombi.shooter.game.util.HighscoreEntry;
import at.zombi.shooter.scene.MainMenuController;

import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String HIGHSCORE_FILE = String.valueOf(HighScoreManager.class.getResource(
        "scores/highscores.dat"
    ));

    public static List<HighscoreEntry> loadHighscores() {
        List<HighscoreEntry> highscores = new ArrayList<>();

        try(ObjectInputStream scoresInputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE))) {
            highscores = (List<HighscoreEntry>) scoresInputStream.readObject();
            // Fuellen der Liste mit defaultwerten, damit im Scoreboard immer 10 Eintraege existieren
            while(highscores.size() < 10) {
                highscores.add(new HighscoreEntry("No Score", 0));
            }
        } catch(FileNotFoundException e) {
            // Fehler, wenn das File nicht existiert -> setzen der Highscores auf einen default Wert
            while(highscores.size() < 10) {
                highscores.add(new HighscoreEntry("No Score", 0));
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return highscores;
    }

    // Schreibt die Hoechstpunktezahlen in das highscores.dat file
    public static void saveHighscores(List<HighscoreEntry> highscores) {
        try(ObjectOutputStream scoresOutputSteam = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE))) {
            scoresOutputSteam.writeObject(highscores);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Fuegt einen Highscore hinzu
    public static void addHighscoreEntry(HighscoreEntry entry, List<HighscoreEntry> highscores) {
        highscores.add(entry);
        sortAndTrimHighscores(highscores);
        saveHighscores(highscores);
    }

    // Sortiert die Highscores - es werden nur die TOP10 gespeichert
    private static void sortAndTrimHighscores(List<HighscoreEntry> highscores) {
        Collections.sort(highscores, (e1, e2) -> Integer.compare(e2.getScore(), e1.getScore()));
        if(highscores.size() > 10) {
            highscores.subList(10, highscores.size()).clear();
        }
    }

    // Setzte die gespeicherten Highscores auf einen Standartwert zurueck
    public static void resetHighscores() {
        List<HighscoreEntry> defaultHighscores = createDefaultHighscores();
        saveHighscores(defaultHighscores);
    }

    // erstellt die Liste mit den Standartwerten
    private static List<HighscoreEntry> createDefaultHighscores() {
        List<HighscoreEntry> defaultHighscores = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            defaultHighscores.add(new HighscoreEntry("No Score", 0));
        }
        return defaultHighscores;
    }
}
