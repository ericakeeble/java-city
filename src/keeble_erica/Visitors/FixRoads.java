//Erica Keeble
package keeble_erica.Visitors;

import keeble_erica.City;
import keeble_erica.Helpers.ConnectAdjacencies;
import keeble_erica.Tiles.*;

/**
 * Implementation of the Visitor design pattern. Changes the symbols of road tiles to point to adjacent roads
 */
public class FixRoads implements Visitor{
    private int index = 0;  //keeps track of where we are in the city array
    private City city;
    private CheckIfRoad vis = new CheckIfRoad();

    /**
     * overwritten function from visitor. Increments this instance's index to keep track of where we are in
     * @param gs greenspace tile
     */
    public void acceptGreenspace(Greenspace gs) {
        ++index;
    }

    /**
     * overwritten function from visitor. Visits tiles on top, bottom,left, and right of the tile indicated by index and
     * changes the symbol if necessary
     * @param rd road tile
     */
    public void acceptRoad(Road rd) {
        int curr_x = x_index();
        int curr_y = y_index();
        ConnectAdjacencies fixRd = new ConnectAdjacencies();

        //visit tiles surrounding the current tile. Returns true if the tile is a road
        /*   GRADING: NESTED   */
        boolean left = visitLeft(curr_x, curr_y);
        boolean right = visitRight(curr_x, curr_y);
        boolean top = visitTop(curr_x, curr_y);
        boolean bot = visitBot(curr_x, curr_y);

        char symbol = fixRd.connectAdjacencies(left, top, bot, right);
        rd.changeSymbol(symbol);

        ++index;
    }

    /**
     * overwritten function from visitor. Increments this instance's index to keep track of where we are in
     * @param em empty tile
     */
    public void acceptEmpty(Empty em) {
        ++index;
    }

    /**
     * overwritten function from visitor. Increments this instance's index to keep track of where we are in
     * @param bld building tile
     */
    public void acceptBuilding(Building bld) {
        ++index;
    }

    /**
     * Visits tile to the left of the location passed in
     * @param x x index of the tile passed in
     * @param y y index of the tile passed in
     * @return true if tile to the left is a road, false otherwise
     */
    private boolean visitLeft(int x, int y) {
        boolean left = false;
        y = y - 1;

        //return if there is no tile to the left
        if(y < 0) {     return false;   }

        //visit the tile
        Tile tile = city.getTile(x, y);
        tile.accept(vis);
        if(vis.isARoad) {   left = true;    }

        return left;
    }

    /**
     * Visits tile to the right of the location passed in
     * @param x x index of the tile passed in
     * @param y y index of the tile passed in
     * @return true if tile to the right is a road, false otherwise
     */
    private boolean visitRight(int x, int y) {
        boolean right = false;
        y = y + 1;

        //return if there is no tile to the right
        if(y >= 5) {     return false;   }

        //visit the tile
        Tile tile = city.getTile(x, y);
        tile.accept(vis);
        if(vis.isARoad) {   right = true;    }

        return right;
    }

    /**
     * Visits tile above the location passed in
     * @param x x index of the tile passed in
     * @param y y index of the tile passed in
     * @return true if tile above is a road, false otherwise
     */
    private boolean visitTop(int x, int y) {
        boolean top = false;
        x = x - 1;

        //return if there is no tile above
        if(x < 0) {     return false;   }

        //visit the tile
        Tile tile = city.getTile(x, y);
        tile.accept(vis);
        if(vis.isARoad) {   top = true;    }

        return top;
    }

    /**
     * Visits tile underneath the location passed in
     * @param x x index of the tile passed in
     * @param y y index of the tile passed in
     * @return true if tile underneath is a road, false otherwise
     */
    private boolean visitBot(int x, int y) {
        boolean bot = false;
        x = x + 1;

        //return if there is no tile below
        if(x >= 5) {     return false;   }

        //visit the tile
        Tile tile = city.getTile(x, y);
        tile.accept(vis);
        if(vis.isARoad) {   bot = true;    }

        return bot;
    }

    /**
     * sets this instance's city to the cite given
     * @param city city to copy into our instance
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * gets x location based on integer index
     * @return x index
     */
    private int x_index() {
        return index % 5;
    }

    /**
     * gets y location based on integer index
     * @return y index
     */
    private int y_index() {
        return (index / 5);
    }
}
