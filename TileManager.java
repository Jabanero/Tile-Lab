import java.util.*;
import java.awt.*;

/**
 * Teddy Lautch
 * D Block Data Structures
 * TileManager
 * Contains a list of tiles and controls reordering as well as adding and removing tiles
 */
public class TileManager {
    private ArrayList<Tile> tiles;
    // This constructor is called every time a new tile manager object is created. 
    // Initially your manager is not storing any tiles.
    public TileManager(){
        tiles = new ArrayList<Tile>();
    }
    // In this method you should add the given tile to the end of your tile 
    // manager's list of tiles.
    public void addTile(Tile rect) {
        tiles.add(rect);
    }
    // This method should cause all of the tiles in the tile manager to draw 
    // themselves on the screen using the given graphical pen. You do not need to do 
    // this yourself directly by calling methods on the Graphics object; each Tile 
    // object already has a draw method that it can use to draw itself. Draw the 
    // tiles from bottom (start) to top (end) of your manager's list.
    // Recall that in order to refer to type Graphics, you must import java.awt.*; 
    // in your code.
    public void drawAll(Graphics g) {
        for (Tile tile : tiles) {
            tile.draw(g);
        }
    }
    // Called when the user left-clicks. It passes you the x/y coordinates the user 
    // clicked. If these coordinates touch any tiles, you should move the topmost of 
    // these tiles to the very top (end) of the list.
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
    // Called when the user Shift-left-clicks. If these coordinates touch any tiles, 
    // you should move the topmost of these tiles to the very bottom (beginning) of 
    // the list.
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
    // Called when the user right-clicks. If these coordinates touch any tiles, you 
    // should delete the topmost of these tiles from the list.
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
    // Called when the user Shift-right-clicks. If these coordinates touch any 
    // tiles, you should delete all such tiles from the list.
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
    // Called when the user types S. This method should perform two actions: (1) 
    // reordering the tiles in the list into a random order, and (2) moving every 
    // tile on the screen to a new random x/y pixel position. The random position 
    // should be such that the square's top-left x/y position is non-negative and 
    // also such that every pixel of the tile is within the passed width and height. 
    // For example, if the width passed is 300 and the height is 200, a tile of size 
    // 20x10 must be moved to a random position such that its top-left x/y position 
    // is between (0, 0) and (280, 190).
    // You can use the built-in Java method Collections.shuffle to randomly 
    // rearrange the elements of your list (step 1).
    public void shuffle(int width, int height) {
        Collections.shuffle(tiles);
        Random rand = new Random();
        for (Tile tile : tiles) {
            tile.setX(rand.nextInt(width - tile.getWidth() + 1));
            tile.setY(rand.nextInt(height - tile.getHeight() + 1));
        }
    }
    private boolean isOnTile(int x, int y, Tile tile){
        return x > tile.getX() && x < tile.getX() + tile.getWidth() && y > tile.getY() && y < tile.getY() + tile.getHeight();
    }
}