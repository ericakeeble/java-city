//Erica Keeble
package keeble_erica;

import java.util.Scanner;

import keeble_erica.Helpers.ColorText;
import keeble_erica.Tiles.Tile;
import keeble_erica.Visitors.ColorTiles;
import keeble_erica.Visitors.Count;
import keeble_erica.Visitors.FixRoads;
import static keeble_erica.Helpers.ColorText.Color.*;
import static keeble_erica.Tiles.Tile.TileType.*;

/**
 * Main menu for the program. Repeats until the user chooses to quit
 */
public class Menu {
    private static City city = new City();
    public static Scanner scanner = new Scanner(System.in);

    /**
     * main, loops through menu until user chooses to quit
     * @param args not used
     */
    public static void main(String[] args) {
        int input = -1;
        //Scanner scanner = new Scanner(System.in);
        city = new City();

        //menu reoccurs until end of input or until input is 0
        while(scanner.hasNext() && input != 0) {
            PrintCity(city);
            PrintMenu();
            input = getMenuInput();

            switch(input) {
                case(1): //Set Tile
                    //get tile type
                    Tile.TileType type = getTileType();
                    if(type == ERR) { break; }

                    //get tile location
                    int[] coords = getTileLocation();
                    if(coords[0] == -1000) { break; }

                    //set tile
                    SetTile(type, coords[0], coords[1]);
                    break;

                case(2): //remove tile
                    //get tile location
                    coords = getTileLocation();
                    if(coords[0] == -1000) { break; }

                    //remove tile
                    RemoveTile(coords[0], coords[1]);
                    break;

                case(3): //make default city
                    MakeDefault();
                    break;

                case(4):
                    CountZones();
                    break;

                case(5): //change tile color
                    //get tile type
                    type = getTileType();
                    if(type == ERR) { break; }

                    //get color
                    ColorText.Color color = getColor();

                    //change color of that tile type
                    SetColor(type, color);
                    break;

                case(6): //fix roads
                    FixRoads();
            }
        }
    }

    /**
     * Constructor
     */
    public Menu() {
    }

    /**
     * Sets tile at location (x,y) to "type"
     * @param type type of tile
     * @param x x location
     * @param y y location
     */
    private static void SetTile(Tile.TileType type, int x, int y) {     city.setTile(type, x, y);   }

    /**
     * Sets tile to Empty type at location (x,y)
     * @param x x coordinate
     * @param y y coordinate
     */
    private static void RemoveTile(int x, int y) {  city.setTile(EMPTY, x, y);  }

    /**
     * Generates default city
     */
    private static void MakeDefault() {
        //clear existing city
        city = new City();

        //enter first row ━ ━ ━ ⚵ ⚵
        city.setTile(ROAD, 0,0);
        city.setTile(ROAD, 0,1);
        city.setTile(ROAD, 0,2);
        city.setTile(GREENSPACE, 0,3);
        city.setTile(GREENSPACE, 0,4);

        //enter second row ━ ━ ━ ⚵ ▫
        city.setTile(ROAD, 1,0);
        city.setTile(ROAD, 1,1);
        city.setTile(ROAD, 1,2);
        city.setTile(GREENSPACE, 1,3);

        //enter third row ━ ━ ━ ⌂ ▫
        city.setTile(ROAD, 2,0);
        city.setTile(ROAD, 2,1);
        city.setTile(ROAD, 2,2);
        city.setTile(BUILDING, 2,3);

        //enter fourth row ▫ ▫ ▫ ⌂ ▫
        city.setTile(BUILDING, 3,3);

        //enter fifth row ▫ ▫ ▫ ▫ ⚵
        city.setTile(GREENSPACE, 4,4);

    }

    /**
     * Calls visitor to count how many of each tile are in the city
     */
    private static void CountZones() {
        Count vis = new Count();
        city.accept(vis);

        System.out.print("Empty: " + vis.getEmptyCount() + "\nGreenspaces: " + vis.getGreenspaceCount() + "\nRoads: "
                + vis.getRoadCount() + "\nBuildings: " + vis.getBuildingCount() + "\n\n");
    }

    /**
     * Calls visitor to change the color of all of a certain type of tile in the city
     * @param type type of tile to change the color of
     * @param color color to change the tile to
     */
    private static void SetColor(Tile.TileType type, ColorText.Color color) {
        ColorTiles vis = new ColorTiles();
        vis.setTileType(type);
        vis.setColor(color);
        city.accept(vis);
    }

    /**
     * Calls visitor to change the symbol of roads that are adjacent to each other
     */
    private static void FixRoads() {
        FixRoads vis = new FixRoads();
        vis.setCity(city);
        city.accept(vis);
    }

    /**
     * Prints the menu to the console
     */
    private static void PrintMenu() {
        System.out.print( "1) Set Tile\n" + "2) Remove tile\n" + "3) Make default City\n" + "4) Count Zones\n"
                        + "5) Set tile color\n" + "6) Fix roads\n" + "0) Quit\n\n" );
    }

    /**
     * Prints the city to the console
     * @param city city which will be printed
     */
    private static void PrintCity(City city) {   System.out.println(city);   }

    /**
     * Gets input from the user.
     * @return menu choice integer
     */
    private static int getMenuInput() {
        int input;

        System.out.print("Choice: ");

        //if input is not integer, label as an error
        input = getNextInt();
        if(input == -1000) {   return -1; }

        if(input > 6 || input < 0) {
            System.out.println("Unknown menu option");
        }

        return input;
    }

    /**
     * Gets next integer from the console input, with error checking. Prints "invalid option" if not an integer
     * @return integer found, or -1000 if error
     */
    private static int getNextInt() {
        int input = -1000;
        try {
            input = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("Invalid option");
            scanner.next();
        }

        return input;
    }

    /**
     * Prompts for tile type, then gets and returns it with error checking
     * @return enum of tile type, type ERR if error
     */
    private static Tile.TileType getTileType() {
        int type;

        //get next int, output message if no int
        System.out.print("Input tile type 1) greenspace 2) building 3) road #) empty: ");
        type = getNextInt();
        if(type == -1000) { return ERR; }

        //default to empty tile if input is out of range
        if(type > 3 || type < 1) {
            type = 0;
        }

        return convertToTileEnum(type);
    }

    /**
     * Converts integer from menu input to tile type enum
     * @param type integer of type from menu
     * @return tile type
     */
    private static Tile.TileType convertToTileEnum(int type) {
        Tile.TileType t;
        switch(type) {
            case(1):
                t = GREENSPACE;
                break;
            case(2):
                t = BUILDING;
                break;
            case(3):
                t = ROAD;
                break;
            default:
                t = EMPTY;
        }
        return t;
    }

    /**
     * Prompts for input location, then gets and returns it with error checking
     * @return integer array of size 2, which holds x coordinate in [0] and y coordinate at [1]
     */
    private static int[] getTileLocation() {
        int[] coords = new int[2];

        //prompt for and get input location
        System.out.print("Input location (x y): ");
        int x_loc, y_loc;
        x_loc = getNextInt();
        y_loc = getNextInt();

        //exit if invalid location
        if(x_loc > 5 || x_loc < 0 || y_loc > 5 || y_loc < 0) {
            System.out.println("Invalid option");
            x_loc = -1000;
        }

        coords[0] = x_loc;
        coords[1]= y_loc;

        return coords;
    }

    /**
     * Prompts for color, then gets and returns enum of color. Defaults to black if error
     * @return enum of color
     */
    private static ColorText.Color getColor() {
        ColorText.Color color;

        System.out.print("Input color 1) red 2) yellow 3) blue 4) green #) black: ");
        int colorChoice = getNextInt();
        if(colorChoice == -1000) { return BLACK; }

        //convert to enum
        switch(colorChoice) {
            case(1):
                color = RED;
                break;
            case(2):
                color = YELLOW;
                break;
            case(3):
                color = BLUE;
                break;
            case(4):
                color = GREEN;
                break;
            default:
                color = BLACK;
                break;
        }
        return color;
    }
}
