//Erica Keeble
package keeble_erica.Visitors;

import keeble_erica.Tiles.Building;
import keeble_erica.Tiles.Empty;
import keeble_erica.Tiles.Greenspace;
import keeble_erica.Tiles.Road;

/**
 * Interface for the visitor design pattern
 */
public interface Visitor {
    void acceptGreenspace(Greenspace gs);

    void acceptRoad(Road rd);

    void acceptEmpty(Empty em);

    void acceptBuilding(Building bld);

}
