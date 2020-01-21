//Erica Keeble
package keeble_erica.Tiles;

import keeble_erica.Helpers.ColorText;
import keeble_erica.Visitors.Visitor;
import static keeble_erica.Helpers.ColorText.colorString;

/**
 * Road class is one implementation of Tile. It has an symbol and color
 */
public class Road extends Tile {
    /**
     * Constructor initializes color to black and symbol to a horizontal line
     */
    public Road() {
        color = ColorText.Color.BLACK;
        symbol = '━';
        coloredSymbol = colorString("━", this.color);
    }

    /**
     * Passes the visitor through
     * @param vis visitor
     */
    public void accept(Visitor vis) {
        vis.acceptRoad(this);
    }

    /**
     * Changes the color of the symbol
     * @param color color which we change the symbol to
     */
    public void changeColor(ColorText.Color color) {
        coloredSymbol = colorString("" + symbol, color);
    }

    /**
     * Changes the symbol
     * @param symbol instance symbol is changed to this
     */
    public void changeSymbol(char symbol) {
        this.symbol = symbol;
        coloredSymbol = colorString("" + symbol, this.color);
    }

}
