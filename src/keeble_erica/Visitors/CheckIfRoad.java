//Erica Keeble
package keeble_erica.Visitors;

import keeble_erica.Tiles.*;

/**
 * Implementation of Visitor design pattern. Checks if the given tile is a road
 */
public class CheckIfRoad implements Visitor {
    boolean isARoad = false;

    /**
     * sets isARoad to false
     * @param gs Greenspace tile
     */
    public void acceptGreenspace(Greenspace gs) {
        isARoad = false;
    }

    /**
     * sets isARoad to true
     * @param rd Road tile
     */
    public void acceptRoad(Road rd) {
        isARoad = true;
    }

    /**
     * sets isARoad to false
     * @param em Empty tile
     */
    public void acceptEmpty(Empty em) {
        isARoad = false;
    }

    /**
     * sets isARoad to false
     * @param bld Building Tile
     */
    public void acceptBuilding(Building bld) {
        isARoad = false;
    }
}
