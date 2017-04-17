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

	/**
	* Initialises the Puzzle Box
	*/
	public PuzzleBox(Vector2 startPos, int tileWidth, int gridSize, String folderLocation, Stage stage) {
		super();

		this.puzzle = new Image [gridSize][gridSize];

		this.tileWidth = tileWidth;
		this.startPos = startPos;
		this.gridSize = gridSize;

		//chooses a random tile to leave empty
		Random rand = new Random();
		int notX = rand.nextInt(gridSize - 1);
		int notY = rand.nextInt(gridSize - 1);

		//populates all sprites, leaving one space empty
        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                //creates a sprite to be placed at the given location, provided the tile hasn't
                //been designated empty
                if (!((i == notX) && (j == notY))){
                    String filename = folderLocation + String.valueOf(i) + String.valueOf(j) + ".jpeg";
                    System.out.println(filename);

                    // Set background image for journal
                    Texture tileImage = new Texture(Gdx.files.internal(filename));

                    this.puzzle[i][j] = new Image(tileImage);
                    int xPos = (int)this.startPos.x + (i * this.tileWidth);
                    int yPos = (int)this.startPos.y + (j* this.tileWidth);
                    this.puzzle[i][j].setPosition(xPos, yPos);
                    this.addClickListner(this.puzzle[i][j], new Vector2(xPos, yPos));
                    stage.addActor(this.puzzle[i][j]);
                }
            }
        }

        //shuffle the tiles randomly
        for (int i = 0; i < 1000; i++){
            //randomly select a tile location
            int xPos = rand.nextInt(gridSize - 1);
            int yPos = rand.nextInt(gridSize - 1);

            if (this.puzzle[xPos][yPos] != null){
                this.shiftTileSelected(new Vector2(xPos, yPos)); //shift the tile
            }
        }
	}

	public void addClickListner(Image image, Vector2 position){
	    image.addListener(new ClickListener() {
            public void click(Actor actor, float x, float y) {
	            shiftTileSelected(position);
	            System.out.println("tile clicked");
	        }
	    });
	}

	public Image getTile(int xPos, int yPos){
	    return this.puzzle [xPos][yPos];
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

	/**
	 * Returns Vector2 of the movable direction. If no move is possible
	 * then (0, 0)  - no move - is returned
	 * @param tileClickedOn
	 * @return Vector2 of the movable direction of the tile
	 */
	public Vector2 getMovableDirection(Vector2 tile){
	    Vector2 move = new Vector2(0, 0);

	    ArrayList <Vector2> availableMoves = new ArrayList();

	    availableMoves.add(new Vector2(-1, 0));
	    availableMoves.add(new Vector2(1, 0));
	    availableMoves.add(new Vector2(0, 1));
	    availableMoves.add(new Vector2(0, -1));

	    for (Vector2 thisMove : availableMoves){
	        int xTry = (int)tile.x + (int)thisMove.x;
	        int yTry = (int)tile.y + (int)thisMove.y;

	        try {
	            if (this.getTile(xTry, yTry) == null){
	                move = thisMove;
	            }
	        } catch(ArrayIndexOutOfBoundsException exception) {
	            System.out.println("out of bounds");
	            System.out.println(xTry);
	            System.out.println(yTry);
	        }
	    }
	    return move;
	}

	/**
	 * Shifts the tile given if possible
	 * @param tile
	 * @return Boolean True if moved, False if tile is left stationary
	 */
	public boolean shiftTileSelected(Vector2 tile){
	    Boolean moved = false;

        if (tile != new Vector2(-1, -1)){
            if (((int)this.getMovableDirection(tile).x != 0) && ((int)this.getMovableDirection(tile).y != 0)){
                int newX = (int)tile.x + (int)this.getMovableDirection(tile).x;
                int newY = (int)tile.y + (int)this.getMovableDirection(tile).y;

                Image mover = this.puzzle[(int)tile.x][(int)tile.y];
                this.puzzle[newX][newY] = mover;
                this.puzzle[(int)tile.x][(int)tile.y] = null;

                int newXPos = (int)this.startPos.x + (newX * this.tileWidth);
                int newYPos = (int)this.startPos.y + (newY * this.tileWidth);

                this.puzzle[newX][newY].setPosition(newXPos, newYPos);

                moved = true;
            }
        }
        return moved;
	}

}
