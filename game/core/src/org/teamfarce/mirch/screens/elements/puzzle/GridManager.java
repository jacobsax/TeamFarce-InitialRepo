package org.teamfarce.mirch.screens.elements.puzzle;

import java.util.ArrayList;

import org.teamfarce.mirch.Vector2Int;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GridManager {
    ArrayList<Tile> tiles;
    public Vector2Int emptyTile;
    int gridSize;

    public GridManager(int gridSize, String folderLocation, Vector2 gridOffset, float tileSize, Stage stage){
        this.tiles = new ArrayList<Tile>();
        this.gridSize = gridSize;
        int count = 0;
        int lost = 13;
        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
            	if (count != lost){
            		String filename = folderLocation + "b" + String.valueOf(i) + String.valueOf(j) + ".jpeg";
	                Tile newTile = new Tile(filename, new Vector2Int(i, j), gridOffset, tileSize, this.tiles.size());
	                
	                final int id = this.tiles.size();
	                newTile.tileImage.addListener(new InputListener() {
	                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                        Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
	                        shiftTile(id);
	                        return false;
	                    }
	        		});
	                
	                stage.addActor(newTile.tileImage);
	                this.tiles.add(newTile);
            	} else {
            		this.tiles.add(null);
            	}
                count++;
            }
        }
    }
    
    /**
	 * Returns Vector2 of the movable direction. If no move is possible
	 * then (0, 0)  - no move - is returned
	 * @param tileClickedOn
	 * @return Vector2 of the movable direction of the tile
	 */
	public Vector2Int getMovableDirection(Tile tileObj){
	    Vector2Int move = new Vector2Int(0, 0);
	    
	    Vector2Int tile = tileObj.getGridPosition();
	    
	     if ((tile.x > 0) && (tile.x < this.gridSize)){
	    	 if ((tile.y > 0) && (tile.y < this.gridSize)){
    		    ArrayList <Vector2Int> availableMoves = new ArrayList<Vector2Int>();

    		    availableMoves.add(new Vector2Int(-1, 0));
    		    availableMoves.add(new Vector2Int(1, 0));
    		    availableMoves.add(new Vector2Int(0, 1));
    		    availableMoves.add(new Vector2Int(0, -1));

    		    for (Vector2Int thisMove : availableMoves){
    		        int xTry = (int)tile.x + (int)thisMove.x;
    		        int yTry = (int)tile.y + (int)thisMove.y;

    		        try {
    		            if (this.getTile(new Vector2Int(xTry, yTry)) == null){
    		                move = thisMove;
    		            }
    		        } catch(ArrayIndexOutOfBoundsException exception) {
    		            System.out.println("out of bounds");
    		            System.out.println(xTry);
    		            System.out.println(yTry);
    		        }
    		    }
	    	 }
	     }
	    
	    return move;
	}
	
	public void shiftTile(int id){
		System.out.println(id);
		//shiftTileSelected(tileFromId(id));
		System.out.println(this.getMovableDirection(this.tileFromId(id)).x);
	}
	
	public Tile tileFromId(int id){
		return this.tiles.get(id);
	}

	/**
	 * Shifts the tile given if possible
	 * @param tile
	 * @return Boolean True if moved, False if tile is left stationary
	 */
	/*
	public boolean shiftTileSelected(Tile tile){
		
    	Boolean moved = false;

        if (!this.getMovableDirection(tile.getGridPosition()).equals(new Vector2Int(0, 0))){

            Vector2Int newPos = tile.getGridPosition().plus(this.getMovableDirection(tile.getGridPosition()));
            Gdx.app.log("Tile Shift Desired", "position (" + newPos.x + ", " + newPos.y + ")");
            
            moved = true;
        } else {
        	System.out.println("Move impossible");
        }

        return moved;
	}
	*/
    
    public Tile[][] convertTo2DArray(){
    	Tile[][] grid = new Tile[this.gridSize][this.gridSize];
    	for (int i = 0; i < this.gridSize; i++){
    		for (int j = 0; j < this.gridSize; j++){
    			grid[i][j] = this.getTile(new Vector2Int(i, j));
    		}
    	}
    	return grid;
    }

    public Tile getTile(Vector2Int pos){
        Tile tile = null;
        if ((pos.x < this.gridSize) && (pos.x > 0)){
            if ((pos.y < this.gridSize) && (pos.y > 0)){
                boolean found = false;
                int counter = 0;
                while (!found && (counter < (this.gridSize * this.gridSize))){
                    Tile temp = this.tiles.get(counter);
                    if (temp != null){
                        if (temp.getGridPosition() == pos){
                            found = true;
                            tile = temp;
                        }
                    }
                    counter++;
                }
            }
        }
        return tile;
    }
    
    public void draw(){
    	
    }


}
