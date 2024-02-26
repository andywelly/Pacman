import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.List;

/**
 * A class for Stationary Ghosts that is a GameObject
 * The player loses a life when they collide with a ghost
 * @author Andwele Ancheta
 */
public class GhostStationary extends GameObject {

    /**
     * GhostStationary takes an Array of coordinates of ghosts
     * @param ghostCoords
     */
    public GhostStationary(List<Point> ghostCoords) {
        this.coords = ghostCoords;
        this.image = new Image ("res/ghostRed.png");
    }

    /**
     * GhostStationary needs its own collisions functions as it
     * is not removed when a collision occurs
     * @param playerRect, rectangle of player
     * @return boolean, true if collision occurs
     */
    @Override
    public boolean collisions(Rectangle playerRect) {
        for (Point coord: coords) {
            Rectangle ghost = image.getBoundingBoxAt(coord);
            if (playerRect.intersects(ghost))  {
                return true;
            }
        }
        return false;
    }


}
