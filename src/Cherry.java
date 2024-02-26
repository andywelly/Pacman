import bagel.Image;
import bagel.util.Point;

import java.util.List;

/**
 * A class for Cherry that is a GameObject
 * The Cherry is Eatable on player collision
 * @author Andwele Ancheta
 */
public class Cherry extends GameObject implements Eatable {
    private final int score = 20;

    /**
     * Cherry Class takes an ArrayList of coordinates
     * of where the Cherries are located
     * @param cherryCoords
     */
    public Cherry(List<Point> cherryCoords) {
        this.coords = cherryCoords;
        this.image = new Image("res/cherry.png");
    }
    /**
     * Getter method for the points a Cherry is worth
     * @return score of Cherry
     */
    @Override
    public int getScore() {
        return score;
    }

}
