//Erica Keeble
package keeble_erica.Tiles;

import keeble_erica.Helpers.ColorText;
import keeble_erica.Visitors.Visitor;

/**
 * Abstract class which is overriden for each of the 4 types of tiles which are listed in the enum
 */
public abstract class Tile {
    /**
     * Enum for the 4 types if tiles, ERR is included only for error checking purposes, it is not a tile type
     */
    public enum TileType{
        GREENSPACE, BUILDING, ROAD, EMPTY, ERR
    }

    char symbol;
    ColorText.Color color;
    String coloredSymbol;

    /**
     * Constructor
     */
    public Tile() {
    }

    /**
     * Must be overriden, accept visitor function for visitor pattern
     * @param vis visitor
     */
    public abstract void accept(Visitor vis);

    /**
     * Must be overriden, changes color of the tile
     * @param color color to change tile
     */
    public abstract void changeColor(ColorText.Color color);

    /**
     * Gets Colored symbol
     * @return colored symbol
     */
    public String getColoredSymbol() {
        return this.coloredSymbol;
    }

}
