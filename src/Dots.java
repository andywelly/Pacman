import bagel.Image;
import bagel.util.Point;

import java.util.List;

/**
 * A class for Dots which is a GameObject
 * Dots are Eatable on collision with the player
 * @author Andwele Ancheta
 */
public class Dots extends GameObject implements Eatable {
    // Score Dots when eaten in 10
    private final int score = 10;

    /**
     * Dots takes an ArrayList of coordinates for each Dot
     * @param dotCoords
     */
    public Dots(List<Point> dotCoords) {
        this.coords = dotCoords;
        this.image = new Image("res/dot.png");
    }

    /**
     * Getter method which returns the points a dot is worth
     * @return score of Dot
     */
    @Override
    public int getScore() {
        return score;
    }


}
