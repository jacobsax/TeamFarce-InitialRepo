package org.teamfarce.mirch.screens.elements.puzzle;

import org.teamfarce.mirch.Vector2Int;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Team FARCE - jacobwunwin
 *
 */
public class Tile {
    Image tileImage;
    Vector2Int gridPos;
    Vector2Int correctGridPos;
    Vector2 gridOffset;
    float tileSize;
    int id;

    public Tile (String image, Vector2Int correctGridPos, Vector2 gridOffset, float tileSize, int id){
    	this.tileSize = tileSize;
    	this.gridOffset = gridOffset;
    	this.correctGridPos = correctGridPos;
        Texture tileTexture = new Texture(Gdx.files.internal(image));
        this.id = id;
        
        this.tileImage = new Image(tileTexture);
        this.updateGridLocation(correctGridPos);
    }
    
    public void updateGridLocation(Vector2Int gridPos){
    	this.gridPos = new Vector2Int(gridPos.x, gridPos.y);
    	float newX =  this.gridOffset.x + (this.gridPos.x * this.tileSize);
    	float newY = this.gridOffset.y + (this.gridPos.y * this.tileSize);
    	this.tileImage.setPosition(newX, newY);
    }
    
    public void shiftGridLocation(Vector2Int shift){
    	Vector2Int newGridPos = this.gridPos;
    	newGridPos.x += shift.x;
    	newGridPos.y += shift.y;
    	this.updateGridLocation(newGridPos);
    }

    Image getImage(){
        return this.tileImage;
    }
    
    public boolean inCorrectPosition(){
    	if ((this.gridPos.x == this.correctGridPos.x) && (this.gridPos.y == this.correctGridPos.y)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public Vector2Int getGridPosition(){
    	return this.gridPos;
    }
    

}
