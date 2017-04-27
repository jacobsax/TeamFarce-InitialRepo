package org.teamfarce.mirch;

import com.badlogic.gdx.Gdx;
import org.teamfarce.mirch.entities.Clue;
import org.teamfarce.mirch.entities.Player;
import org.teamfarce.mirch.entities.Suspect;
import org.teamfarce.mirch.map.Map;
import org.teamfarce.mirch.map.Room;
import org.teamfarce.mirch.screens.AbstractScreen;
import org.teamfarce.mirch.screens.elements.puzzle.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Stores a snapshot of the game state.
 */
public class GameSnapshot {
    /**
     * Indicates whether the game has been won.
     */
    public boolean gameWon;
    /**
     * Holds the journal associated with this state.
     */
    public Journal journal;
    public AbstractScreen journalScreen;
    public Map map;
    public Suspect victim;
    public Suspect murderer;
    public Clue meansClue;
    MIRCH game;
    List<Clue> clues;

    int currentPersonality;
    private GameState state;
    private Suspect interviewSuspect = null;

    public final ScoreTracker scoreTracker = new ScoreTracker();
    public Player player;
    public List<Room> rooms;
    public List<Suspect> suspects;

    private static final String singlePlayerLooseMsg =
        "You messed up mate.\n\n%s has deduced that it was %s who killed %s in the %s with %s.";

    // List of other detectives who could've possibly solved the crime
    private static final String[] detectives = new String[] {
        "Richie Paper",
        "Princess Fiona",
        "Lilly Blort",
        "Michael Dodders"
    };

    /**
     * Initialises function.
     */
    GameSnapshot(MIRCH game, Map map, List<Room> rooms, List<Suspect> suspects, List<Clue> clues) {
        this.game = game;
        this.suspects = suspects;
        this.state = GameState.menu;
        this.clues = clues;
        this.map = map;
        this.rooms = rooms;
        this.journal = new Journal(game);
        this.gameWon = false;
        this.currentPersonality = 0;
    }

    /**
     * This method shows the narrator screen with the necessary dialog for the player losing the
     * game.
     */
    public void showLoseScreen() {
        final String murdererName = murderer.getName();
        final String victimName = victim.getName();
        final String weapon = meansClue.getName();
        final String otherDetective = detectives[new Random().nextInt(detectives.length)];

        final String room = game
            // First get a stream of the rooms in this game.
            .getCurrentGameSnapshot()
            .map
            .getRooms()
            .stream()
            // Select the room which is the murder room.
            .filter(r -> r.isMurderRoom())
            .findFirst()
            // We should be guaranteed to have one room which matches.
            .get()
            // We want its name.
            .getName();

        final String endMsg = String.format(
            singlePlayerLooseMsg, otherDetective, murdererName, victimName, room, weapon
        );

        this.game
            .guiController
            .narratorScreen
            .setSpeech(endMsg)
            .setButton("End Game", () -> Gdx.app.exit());

        this.game.getCurrentGameSnapshot().setState(GameState.narrator);
    }

    /**
     * Getter for current score
     *
     * @return Returns current score.
     */

    public int getScore() {
        final int score = this.scoreTracker.collectScore(new ScoreCalculator());

        if (score <= 0) {
            this.showLoseScreen();
        }

        return score;
    }

    /**
     * Returns a list of all rooms.
     *
     * @return The rooms.
     */
    List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Returns a list of all props.
     *
     * @return The props.
     */
    public List<Clue> getClues() {
        return this.clues;
    }

    /**
     * Returns true if the means of the murder has been proven.
     *
     * @return Whether we have "proven" the means.
     */
    public boolean isMeansProven() {
        return journal.hasFoundMurderWeapon();
    }

    /**
     * Returns true if the motive of the murder has been proven.
     *
     * @return Whether we have "proven" the motive.
     */
    public boolean isMotiveProven() {
        return journal.hasFoundMotiveClue();
    }

    /**
     * Returns the current game state.
     *
     * @return The game state.
     */
    public GameState getState() {
        return this.state;
    }

    /**
     * Allows the setting of the game state.
     *
     * @param state The state to set.
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Returns a list of all suspects.
     *
     * @return The suspects.
     */
    public List<Suspect> getSuspects() {
        return this.suspects;
    }

    public Suspect getSuspectForInterview() {
        return interviewSuspect;
    }

    public void setSuspectForInterview(Suspect s) {
        interviewSuspect = s;
    }

    /**
     * Adds the prop to the journal.
     *
     * <p>
     * This tells the journal to keep a log of this prop.
     * </p>
     *
     * @param clue The clue to add.
     */
    public void journalAddClue(Clue clue) {
        this.journal.foundClues.add(clue);

    }

    /**
     * Getter for current personality
     *
     * @return Returns current personality score.
     */

    public int getPersonality() {
        return this.currentPersonality;
    }

    /**
     * Updates current personality of player in game
     *
     * @param amount Amount to modify personality score by
     */
    public void modifyPersonality(int amount) {
        if (this.currentPersonality > -10 && this.currentPersonality < 10) {
            this.currentPersonality += amount;
        }
    }

    /**
     * This method unlocks all the Suspects and allows them all to be spoken to.
     */
    public void setAllUnlocked() {
        for (Suspect s: getSuspects()) {
            s.setLocked(false);
        }
    }
}
