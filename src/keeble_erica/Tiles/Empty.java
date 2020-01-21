//Erica Keeble
package keeble_erica.Tiles;

import keeble_erica.Helpers.ColorText;
import keeble_erica.Visitors.Visitor;
import static keeble_erica.Helpers.ColorText.colorString;

/**
 * Empty class is one implementation of Tile. It has an symbol and color
 */
public class Empty extends Tile {

    /**
     * Constructor initializes color to black and symbol to a little box
     */
    public Empty() {
        color = ColorText.Color.BLACK;
        symbol = '▫';
        coloredSymbol = colorString("▫", color);
    }

    /**
     * Passes the visitor through
     * @param vis visitor
     */
    public void accept(Visitor vis) {
        vis.acceptEmpty(this);
    }

    /**
     * Changes the color of the symbol
     * @param color color which we change the symbol to
     */
    public void changeColor(ColorText.Color color) {
        coloredSymbol = colorString("▫", color);
    }

}
