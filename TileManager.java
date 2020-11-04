import java.util.*;
import java.awt.*;

/**
 * Teddy Lautch
 * D Block Data Structures
 * TileManager
 * Contains a list of tiles and controls reordering as 
 * well as adding and removing tiles
 */
public class TileManager {
    private ArrayList<Tile> tiles;
    /**
     * No parameter constructor initializes ArrayList of Tiles
     */
    public TileManager(){
        tiles = new ArrayList<Tile>();
    }
    /**
     * Takes in a Tile object and adds it to the end of the list
     * @param rect
     * This is the tile to be added
     */
    public void addTile(Tile rect) {
        tiles.add(rect);
    }
    /**
     * Uses Tile draw method to draw all objects in list.
     * Tile objects are drawn from beginning to end, with the
     * first tiles on the bottom
     * @param g
     * Graphics object used to draw the Tile Objects
     */
    public void drawAll(Graphics g) {
        for (Tile tile : tiles) {
            tile.draw(g);
        }
    }
    /**
     * Moves the topmost/(nearest to the end of the list) Tile under the coordinates
     * to the top/(end of the list)
     * @param x
     * The X coordinate used to determine selectionn
     * @param y
     * The Y coordinate used to determine selectionn
     */
    public void raise(int x, int y) {
        int i = tiles.size() - 1;
        while (i >= 0) {
            if (isOnTile(x, y, tiles.get(i))) {
                tiles.add(tiles.size(), tiles.get(i));
                tiles.remove(i);
                i = 0;
            }
            i--;
            
        }
    }
    /**
     * Moves the topmost/(nearest to the end of the list) Tile under the coordinates
     * to the bottom/(start of the list)
     * @param x
     * The X coordinate used to determine selection
     * @param y
     * The Y coordinate used to determine selection
     */
    public void lower(int x, int y) {
        int i = tiles.size() - 1;
        while (i >= 0) {
            if (isOnTile(x, y, tiles.get(i))) {
                tiles.add(0, tiles.get(i));
                tiles.remove(i + 1);
                i = 0;
            }
            i--;
        }
    }
    /**
     * Removes the topmost/(nearest to the end of the list) Tile under the coordinates
     * from the list
     * @param x
     * The X coordinate used to determine selection
     * @param y
     * The Y coordinate used to determine selectio
     */
    public void delete(int x, int y) {
        int i = tiles.size() - 1;
        while (i >= 0) {
            if (isOnTile(x, y, tiles.get(i))) {
                tiles.remove(i);
                i = 0;
            }
            i--;
        }
    }
    /**
     * Removes all Tile objects under the coordinates from the list
     * @param x
     * The X coordinate used to determine selection
     * @param y
     * The Y coordinate used to determine selection
     */
    public void deleteAll(int x, int y) {
        int i = 0;
        while (i < tiles.size()) {
            if (isOnTile(x, y, tiles.get(i))) {
                tiles.remove(i);
                i--;
            }
            i++;
        }
    }
    /**
     * Shuffles the order of the list of Tile objects and randomizes all Tile positions
     * @param width
     * The horizontal limit for where the Tile positions can be randomized to
     * @param height
     * The vertical limit for where the Tile positions can be randomized to
     */
    public void shuffle(int width, int height) {
        Collections.shuffle(tiles);
        Random rand = new Random();
        for (Tile tile : tiles) {
            tile.setX(rand.nextInt(width - tile.getWidth() + 1));
            tile.setY(rand.nextInt(height - tile.getHeight() + 1));
        }
    }
    /**
     * Determines if the given x and y coordinates are over a Tile
     * @param x
     * The X coordinate used to determine selection
     * @param y
     * The Y coordinate used to determine selection
     * @param tile
     * The Tile Object that the coordinates are compared to
     * @return
     * Returns a boolean, true if the coordinates are within the Tile and false otherwise
     */
    private boolean isOnTile(int x, int y, Tile tile){
        return x > tile.getX() && x < tile.getX() + tile.getWidth() && y > tile.getY() && y < tile.getY() + tile.getHeight();
    }
}