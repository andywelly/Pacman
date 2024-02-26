import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.List;

/**
 * Walls class is a gameObject
 * But it interacts differently with player collisions
 * it has to Override the collisions function
 * @author Andwele Ancheta
 */
public class Walls extends GameObject {

    /**
     * Walls takes an input of an ArrayList of coordinates of walls
     * @param wallCoords
     */
    public Walls(List<Point> wallCoords) {
        this.coords = wallCoords;
        this.image = new Image("res/wall.png");
    }

    /**
     * When player collisions occur with walls different interaction occurs
     * The object is not removed when a collision occurs it has to be Overriden
     * @param playerRect, rectangle of player
     * @return true if collision occurs
     */
    @Override
    public boolean collisions(Rectangle playerRect) {
        for (Point coord: coords) {
            Rectangle wall = image.getBoundingBoxAt(coord);
            if (playerRect.intersects(wall))  {
                return true;
            }
        }
        return false;
    }



}
