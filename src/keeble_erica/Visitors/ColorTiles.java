//Erica Keeble
package keeble_erica.Visitors;

import keeble_erica.Helpers.ColorText;
import keeble_erica.Tiles.*;
import static keeble_erica.Tiles.Tile.TileType.*;

/**
 * Implementation of the Visitor design pattern. Changes the color of the given tile
 */
public class ColorTiles implements Visitor{
    private Tile.TileType type;
    private ColorText.Color color;

    public void setTileType(Tile.TileType t) { type = t; }
    public void setColor(ColorText.Color c) { color = c; }

    /**
     * Changes greenspace tile to instance color
     * @param gs Greenspace tile
     */
    public void acceptGreenspace(Greenspace gs) {
        if(type == GREENSPACE) {
            gs.changeColor(color);
        }
    }

    /**
     * Changes road tile to instance color
     * @param rd Road tile
     */
    public void acceptRoad(Road rd) {
        if(type == ROAD) {
            rd.changeColor(color);
        }
    }

    /**
     * Changes empty tile to instance color
     * @param em Empty tile
     */
    public void acceptEmpty(Empty em) {
        if(type == EMPTY) {
            em.changeColor(color);
        }
    }

    /**
     * Changes building tile to instance color
     * @param bld Building tile
     */
    public void acceptBuilding(Building bld) {
        if(type == BUILDING) {
            bld.changeColor(color);
        }
    }
}
