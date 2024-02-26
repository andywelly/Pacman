import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.List;

/**
 * An abstract class for stationary GameObjects
 * @author Andwele Ancheta
 */
public abstract class GameObject {

    /**
     * These are protected as each subclass will
     * change these values in their constructors
     */
    protected Image image;
    protected List<Point> coords;

    /**
     * draw function iterates over ArrayList and draws the given image
     */
    public void draw() {
        for (Point coord: coords) {
            image.drawFromTopLeft(coord.x, coord.y);
        }
    }

    /**
     * collisions functions takes the rectangle of the player as an input
     * If any of the objects intersect with the player
     * It is removed and true is returned
     * @param playerRect, rectangle of player
     * @return boolean, true if an intersection occurs
     */
    public boolean collisions(Rectangle playerRect) {
        for (Point coord: coords) {
            Rectangle object = image.getBoundingBoxAt(coord);
            if (playerRect.intersects(object)) {
                coords.remove(coord);
                return true;
            }
        }
        return false;
    }

}
