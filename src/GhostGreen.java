import bagel.Image;
import bagel.util.Point;

import java.util.Random;

/**
 * A class for a blueGhost which extends Ghost
 * Ghosts are Eatable when in Frenzy
 * The Green ghost travel direction is randomly vertical or horizontal
 * @author Andwele Ancheta
 */
public class GhostGreen extends Ghost implements Eatable{
    Random rand = new Random();
    private final int score = 30;
    private String direction;

    /**
     * Represents a green Ghost that is instantiated in the level class
     * GhostGreen extends from the Ghost abstract class and implements Eatable
     * A random direction of travel is chosen on creation
     * the green ghost travels either vertical or horizontal
     * @param ghostCoord
     */
    public GhostGreen(Point ghostCoord) {
        this.ghostCoord = ghostCoord;
        this.image = new Image ("res/ghostGreen.png");
        this.speed = 4;
        if (rand.nextInt(2) == 1) {
            direction = "vertical";
        } else {
            direction = "horizontal";
        }
    }


    /**
     * Function to the direction of travel to the opposite when it hits a wall
     * When inFrenzy the speed is decreased to 3.5
     */
    @Override
    public void movement() {
        if (inFrenzy) {
            this.speed = 3.5;
        } else {
            this.speed = 4;
        }
        // Switch block to determine what the opposite direction of travel is
        switch (direction) {
            case "vertical":
                if (initialDirection) {
                    moveDown();
                } else {
                    moveUp();
                }
                break;
            case "horizontal":
                if (initialDirection) {
                    moveRight();
                } else {
                    moveLeft();
                }
                break;
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
