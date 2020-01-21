//Erica Keeble
package keeble_erica;

import keeble_erica.Tiles.*;
import keeble_erica.Visitors.Visitor;

/**
 * Holds and manages the 5x5 map of tiles which make up the city
 */
public class City {
    private Tile[][] cityMap = new Tile[5][5];

    /**
     * Constructor
     */
    City() {
        InitMap();
    }

    private void InitMap() {
        //fill cityMap with Empty tiles
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                cityMap[i][j] = new Empty();
            }
        }
    }

    /**
     * Accepts visitor for visitor design pattern
     * @param vis visitor to accept
     */
    void accept(Visitor vis) {

        /*   GRADING: COUNT   */
        /*   GRADING: COLOR   */
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                cityMap[j][i].accept(vis);
                //System.out.println(this.toString());
            }
        }
    }


    /**
     * Sets the tile at location (x,y) to the "type"
     * @param type type to set the tile
     * @param x_loc x coordinate
     * @param y_loc y coordinate
     */
    void setTile(Tile.TileType type, int x_loc, int y_loc) {
        switch(type) {
            case GREENSPACE:
                cityMap[x_loc][y_loc] = new Greenspace();
                break;
            case BUILDING:
                cityMap[x_loc][y_loc] = new Building();
                break;
            case ROAD:
                cityMap[x_loc][y_loc] = new Road();
                break;
            case EMPTY:
                cityMap[x_loc][y_loc] = new Empty();
        }
    }

    /**
     * Gets tile at location (x,y)
     * @param x_loc x coordinate
     * @param y_loc y coordinate
     * @return tile at location (x,y)
     */
    public Tile getTile(int x_loc, int y_loc) {
        return cityMap[x_loc][y_loc];
    }

    /**
     * Overriden toString which builds a string of the whole city map laid out
     * @return string which contains city map
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                output.append(cityMap[i][j].getColoredSymbol());
            }
            output.append("\n");
        }

        return output.toString();
    }
}
