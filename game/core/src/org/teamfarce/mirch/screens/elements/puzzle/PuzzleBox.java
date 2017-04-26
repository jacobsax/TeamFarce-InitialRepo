package org.teamfarce.mirch.screens.elements.puzzle;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Used to generate the GUI and logic required for the puzzle box.
 * @author Team FARCE - Jacob W Unwin
**/
public class PuzzleBox{

    Image [][] puzzle;
    int tileWidth;
    int gridSize;
    Vector2 startPos;
    long lastPressTime = 0;
    int pressWaitTime = 1;
    Stage stage;
    int lastMouseX = 0;
    int lastMouseY = 0;
    
    GridManager gridManager;

	/**
	* Initialises the Puzzle Box
	*/
	public PuzzleBox(Vector2 startPos, int tileWidth, int gridSize, String folderLocation, Stage stage) {
		super();
		this.stage = stage;
		this.gridManager = new GridManager(gridSize, folderLocation, startPos, tileWidth , stage);
	}
}
