import bagel.Image;
import bagel.util.Point;

/**
 * A class for a red ghost which extends Ghost
 * Ghosts are Eatable when in Frenzy
 * The Red ghost travel direction is horizontal
 * @author Andwele Ancheta
 */
public class GhostRed extends Ghost implements Eatable{
    private static final int score = 30;

    /**
     * Represents a Red Ghost that is instantiated in the level class
     * GhostRed extends from the Ghost abstract class and implements Eatable
     * @param ghostCoord
     */
    public GhostRed(Point ghostCoord) {
        this.ghostCoord = ghostCoord;
        this.image = new Image("res/ghostRed.png");
        this.speed = 1;
    }

    /**
     * Function to the direction of travel to the opposite direction when it hits a wall
     * When inFrenzy the speed is decreased to 0.5
     */
    @Override
    public void movement() {
        if (inFrenzy) {
            this.speed = 0.5;
        } else {
            this.speed = 1;
        }
        if (initialDirection) {
            moveLeft();
        } else {
            moveRight();
        }
    }

    /**
     * Function called to get how many points the ghost is when eaten in frenzy
     * @return score when ghost is eaten
     */
    @Override
    public int getScore() {
        return score;
    }
}
