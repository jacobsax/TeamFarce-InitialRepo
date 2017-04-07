package org.teamfarce.mirch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.teamfarce.mirch.Assets;
import org.teamfarce.mirch.GameSnapshot;
import org.teamfarce.mirch.GameState;
import org.teamfarce.mirch.MIRCH;

/**
 * @author Team FARCE - Jacob W Unwin
 */
public class PuzzleBoxScreen extends AbstractScreen {

    /**
     * The width of the menu
     */
    private static final int BUTTON_WIDTH = Gdx.graphics.getWidth() / 3;
    private static final int BUTTON_HEIGHT = 80;
    private static final int CENTER_MARGIN = 30;

    // Initialising necessary objects and variables
    /**
     * the stage to render the menu to
     */
    public Stage stage;
    /**
     * This is the referencing to the game snapshot
     */
    private GameSnapshot gameSnapshot;

    /**
     * Constructor for the menu
     *
     * @param game - The game object the menu is being loaded for
     */
    public MainMenuScreen(final MIRCH game, Skin uiSkin) {

        super(game);
        this.gameSnapshot = game.getCurrentGameSnapshot();
        this.stage = this.initStage();
    }



    /**
     * Initialises the new stage.
    **/
    public Stage initStage(){
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        return stage;
    }

    /**
     * This method is called to render the main menu to the stage
     */
    public void render(float delta) {
        // Determining the background colour of the menu
        Gdx.gl.glClearColor(135, 206, 235, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Rendering the buttons
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
    }

    /**
     * This method disposes of all elements
     */
    public void dispose() {
        // Called when disposing the main menu
        stage.dispose();
    }

    /**
     * This method is called when the window is resized.
     *
     * @param width - The new width
     * @param height - The new height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

	/**
	* Default empty PuzzleBoxScreen constructor
	*/
	public PuzzleBoxScreen() {
		super();
	}

	/**
	* Default PuzzleBoxScreen constructor
	*/
	public PuzzleBoxScreen(Stage stage, GameSnapshot gameSnapshot, Stage initStage) {
		super();
		this.stage = stage;
		this.gameSnapshot = gameSnapshot;
		this.initStage = initStage;
	}

	/**
	* Returns value of BUTTON_WIDTH
	* @return
	*/
	public static int getBUTTON_WIDTH() {
		return BUTTON_WIDTH;
	}

	/**
	* Returns value of BUTTON_HEIGHT
	* @return
	*/
	public static int getBUTTON_HEIGHT() {
		return BUTTON_HEIGHT;
	}

	/**
	* Returns value of CENTER_MARGIN
	* @return
	*/
	public static int getCENTER_MARGIN() {
		return CENTER_MARGIN;
	}

	/**
	* Returns value of stage
	* @return
	*/
	public Stage getStage() {
		return stage;
	}

	/**
	* Sets new value of stage
	* @param
	*/
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	* Returns value of gameSnapshot
	* @return
	*/
	public GameSnapshot getGameSnapshot() {
		return gameSnapshot;
	}

	/**
	* Sets new value of gameSnapshot
	* @param
	*/
	public void setGameSnapshot(GameSnapshot gameSnapshot) {
		this.gameSnapshot = gameSnapshot;
	}

	/**
	* Returns value of initStage
	* @return
	*/
	public Stage getInitStage() {
		return initStage;
	}

	/**
	* Sets new value of initStage
	* @param
	*/
	public void setInitStage(Stage initStage) {
		this.initStage = initStage;
	}
};
