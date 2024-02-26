import bagel.Image;
import bagel.util.Point;

/**
 * A class for a blueGhost which extends Ghost
 * Ghosts are Eatable when in Frenzy
 * The blue ghost travels up and down
 * @author Andwele Ancheta
 */
public class GhostBlue extends Ghost implements Eatable{
    private final int score = 30;

    /**
     * Represents a blue Ghost that is instantiated in the level class
     * GhostBlue extends from the Ghost abstract class and implements Eatable
     * @param ghostCoord
     */
    public GhostBlue(Point ghostCoord) {
        this.ghostCoord = ghostCoord;
        this.image = new Image("res/ghostBlue.png");
        this.speed = 2;
    }


    /**
     * Function to move the ghost up and down and change speed when in frenzy
     */
    @Override
    public void movement() {
        // when in Frenzy the speed is given to be 1.5
        if (inFrenzy) {
            this.speed = 1.5;
        } else {
            this.speed = 2;
        }
        // Changes to opposite direction when a wall is hit
        if (initialDirection) {
            moveDown();
        } else {
            moveUp();
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
