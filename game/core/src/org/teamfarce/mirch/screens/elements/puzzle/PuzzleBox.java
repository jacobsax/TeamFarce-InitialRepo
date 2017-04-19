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

	/**
	 * Returns a limited minimum value
	 * @param f
	 * @param min
	 * @return
	 */
	public int minLimitInt(int f, int min){
	    if (f >= min){
	        return f;
	    } else {
	        return min;
	    }
	}

	public int maxLimitInt(int value, int max){
	    if (value <= max){
	        return value;
	    } else {
	        return max;
	    }
	}

	public int combinedLimitInt(int value, int min, int max){
	    return this.maxLimitInt(this.minLimitInt(value, min), max);
	}

	

}
