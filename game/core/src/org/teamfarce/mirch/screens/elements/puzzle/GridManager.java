package org.teamfarce.mirch.screens.elements.puzzle;

import java.util.ArrayList;

import org.teamfarce.mirch.Vector2Int;

public class GridManager {
    ArrayList<Tile> tiles;
    public Vector2Int emptyTile;
    int gridSize;

    public GridManager(int gridSize, String folderLocation){
        this.tiles = new ArrayList<Tile>();
        this.gridSize = gridSize;

        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                String filename = folderLocation + String.valueOf(i) + String.valueOf(j) + ".jpeg";
                this.tiles.add(new Tile(filename, new Vector2Int(i, j)));
            }
        }
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
                        counter++;
                    }
                }
            }
        }
        return tile;
    }


}
