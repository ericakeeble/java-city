//Erica Keeble
package keeble_erica.Visitors;

import keeble_erica.Tiles.*;

/**
 * Implementation of the Visitor design pattern. Counts the number of each different kind of tile in the city
 */
public class Count implements Visitor {
    private int greenspaceCount = 0;
    private int buildingCount = 0;
    private int roadCount = 0;
    private int emptyCount = 0;

    //getters
    public int getGreenspaceCount() {  return greenspaceCount;  }
    public int getBuildingCount() {  return buildingCount;  }
    public int getRoadCount() {  return roadCount;  }
    public int getEmptyCount() {  return emptyCount;  }

    /**
     * Constructor
     */
    public Count() {
    }

    /**
     * increments greenspace count
     * @param gs greenspace tile
     */
    public void acceptGreenspace(Greenspace gs) {
        ++greenspaceCount;
    }

    /**
     * increments road count
     * @param rd road tile
     */
    public void acceptRoad(Road rd) {
        ++roadCount;
    }

    /**
     * increments empty count
     * @param em empty tile
     */
    public void acceptEmpty(Empty em) {
        ++emptyCount;
    }

    /**
     * increments building count
     * @param bld building tile
     */
    public void acceptBuilding(Building bld) {
        ++buildingCount;
    }
}
