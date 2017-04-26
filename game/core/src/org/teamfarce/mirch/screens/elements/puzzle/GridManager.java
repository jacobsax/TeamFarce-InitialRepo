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

    public boolean gridSolved(){
    	boolean solved = true;
    	
    	for (int i = 0; i < this.tiles.size(); i++){
    		if (this.tiles.get(i) != null){
	    		if (!this.tiles.get(i).inCorrectPosition()){
	    			solved = false;
	    		}
    		}
    	}
    	
    	return solved;
    }
    
    /**
	 * Returns Vector2 of the movable direction. If no move is possible
	 * then (0, 0)  - no move - is returned
	 * @param tileClickedOn
	 * @return Vector2 of the movable direction of the tile
	 */
	public Vector2Int getMovableDirection(Tile tileObj){
		System.out.println("Finding movable direction");
	    Vector2Int move = new Vector2Int(0, 0);
	    System.out.println(tileObj.getGridPosition());
	    
	    Vector2Int tile = tileObj.getGridPosition();
	    
	    ArrayList <Vector2Int> availableMoves = new ArrayList<Vector2Int>();

	    availableMoves.add(new Vector2Int(-1, 0));
	    availableMoves.add(new Vector2Int(1, 0));
	    availableMoves.add(new Vector2Int(0, 1));
	    availableMoves.add(new Vector2Int(0, -1));
	    System.out.println("Generated all available moves");

	    for (Vector2Int thisMove : availableMoves){
	        int xTry = (int)tile.x + (int)thisMove.x;
	        int yTry = (int)tile.y + (int)thisMove.y;
	        
	        if ((xTry >= 0) && (xTry < this.gridSize)){
		    	 if ((yTry >= 0) && (yTry < this.gridSize)){
		    		
		    		 if (this.getTile(new Vector2Int(xTry, yTry)) == null){
			                move = thisMove;
			            }
		    	 }
	        }
	    }
	    return move;
	}
	
	public void shiftTile(int id){
		System.out.println(id);
		Vector2Int movableDirection = this.getMovableDirection(this.tileFromId(id));
		System.out.println(movableDirection);
		this.tileFromId(id).shiftGridLocation(movableDirection);
        Gdx.app.log("Tile Shift triggered", "position (" + movableDirection.x + ", " + movableDirection.y + ")");
        System.out.print(this.gridSolved());
	}
	
	public Tile tileFromId(int id){
		return this.tiles.get(id);
	}
    
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
    	System.out.println("Finding tile vrom VectorInt Position");
    	System.out.println(pos);
        Tile tile = null;
        boolean found = false;

        if ((pos.x < this.gridSize) && (pos.x >= 0)){
            if ((pos.y < this.gridSize) && (pos.y >= 0)){
            	System.out.println("Searching for tile");
                int counter = 0;
                while (!found && (counter < (this.gridSize * this.gridSize))){
                    Tile temp = this.tiles.get(counter);
                    if (temp != null){
                    	System.out.println(temp.getGridPosition());
                        if (temp.getGridPosition().x == pos.x){
                        	if (temp.getGridPosition().y == pos.y){
	                            found = true;
	                            tile = temp;
	                            System.out.println("Tile found");
	                            System.out.println(temp.getGridPosition());
                        	}
                        }
                    }
                    counter++;
                }
            }
        }
        System.out.println(found);
        return tile;
    }
    
    public void draw(){
    	
    }


}
