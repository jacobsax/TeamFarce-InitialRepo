package org.teamfarce.mirch.screens.elements.puzzle;

import org.teamfarce.mirch.Vector2Int;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Team FARCE - jacobwunwin
 *
 */
public class Tile {
    Image tileImage;
    Vector2Int gridPos;

    public Tile (String image, Vector2Int gridPos){

    }

    Vector2Int getGridPosition(){
        return this.gridPos;
    }

    Image getImage(){
        return this.tileImage;
    }


}
