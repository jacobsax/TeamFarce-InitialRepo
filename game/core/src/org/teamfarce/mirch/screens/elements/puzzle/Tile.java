package org.teamfarce.mirch.screens.elements.puzzle;

import org.teamfarce.mirch.Vector2Int;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * Stores an individual Tile for a PuzzleBox
 * @author TeamFarce - Jacob Unwin
 *
 */
public class Tile {
    Image tileImage;
    Vector2Int gridPos;
    Vector2Int correctGridPos;
    Vector2 gridOffset;
    float tileSize;
    int id;

    /**
     * Initiates the Tile
     * @param image - the path to the tile image
     * @param correctGridPos - the correct grid position of the tile
     * @param gridOffset - the offset of the grid from the corner of the window (in pixels)
     * @param tileSize - the size of each tile
     * @param id - the tile ID, this should be unique between sets of tiles
     */
    public Tile (String image, Vector2Int correctGridPos, Vector2 gridOffset, float tileSize, int id){
    	this.tileSize = tileSize;
    	this.gridOffset = gridOffset;
    	this.correctGridPos = correctGridPos;
        Texture tileTexture = new Texture(Gdx.files.internal(image));
        this.id = id;
        
        this.tileImage = new Image(tileTexture);
        this.updateGridLocation(correctGridPos);
    }
    
    /**
     * Updates the grid location of the tile, and also moves its position on the screen
     * @param gridPos - the new grid location
     */
    public void updateGridLocation(Vector2Int gridPos){
    	this.gridPos = new Vector2Int(gridPos.x, gridPos.y);
    	float newX =  this.gridOffset.x + (this.gridPos.x * this.tileSize);
    	float newY = this.gridOffset.y + (this.gridPos.y * this.tileSize);
    	this.tileImage.setPosition(newX, newY);
    }
    
    /**
     * Shifts the tile location based on the offset passed into the procedure. 
     * @param shift
     */
    public void shiftGridLocation(Vector2Int shift){
    	Vector2Int newGridPos = this.gridPos;
    	newGridPos.x += shift.x;
    	newGridPos.y += shift.y;
    	this.updateGridLocation(newGridPos);
    }

    /**
     * Returns the Image object of the tile
     * @return
     */
    Image getImage(){
        return this.tileImage;
    }
    
    /**
     * Checks if the tile is in its correct grid position for the puzzle to be complete.
     * @return Boolean True if tile is in correct grid pos, False otherwise
     */
    public boolean inCorrectPosition(){
    	if ((this.gridPos.x == this.correctGridPos.x) && (this.gridPos.y == this.correctGridPos.y)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Returns the current grid position of the tile
     * @return Vector2Int
     */
    public Vector2Int getGridPosition(){
    	return this.gridPos;
    }
    

}
