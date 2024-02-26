import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.Random;

/**
 * A class for a blueGhost which extends Ghost
 * Ghosts are Eatable when in Frenzy
 * The pink ghost direction is randomly chosen when it hits a wall
 * @author Andwele Ancheta
 */
public class GhostPink extends Ghost implements Eatable {
    Random rand = new Random();
    private static final int score = 30;
    private String direction;

    /**
     * Represents a Pink Ghost that is instantiated in the level class
     * GhostPink extends from the Ghost abstract class and implements Eatable
     * A random intial direction is chosen within the constructor
     * @param ghostCoord
     */
    public GhostPink(Point ghostCoord) {
        this.ghostCoord = ghostCoord;
        this.image = new Image ("res/ghostPink.png");
        this.speed = 3;
        int randomInt = rand.nextInt(4);
        switch (randomInt) {
            case 0:
                direction = "Left";
                break;
            case 1:
                direction = "Right";
                break;
            case 2:
                direction = "Up";
                break;
            case 3:
                direction = "Down";
                break;

        }

    }

    /**
     * Function to changeDirection of the pink Ghost randomly when it hits a wall
     * One of the other three directions are randomly chosen to move towards
     */
    @Override
    public void changeDirection() {
        // Bound of 3 as ghost cannot keep moving in current direction
        int temp = rand.nextInt(3);
        switch (direction) {
            case "Left":
                if (temp == 0) {
                    direction = "Right";
                } else if (temp == 1) {
                    direction = "Up";
                } else {
                    direction = "Down";
                }
                break;
            case "Right":
                if (temp == 0) {
                    direction = "Left";
                } else if (temp == 1) {
                    direction = "Up";
                } else {
                    direction = "Down";
                }
                break;
            case "Up":
                if (temp == 0) {
                    direction = "Left";
                } else if (temp == 1) {
                    direction = "Right";
                } else {
                    direction = "Down";
                }
                break;
            case "Down":
                if (temp == 0) {
                    direction = "Left";
                } else if (temp == 1) {
                    direction = "Right";
                } else {
                    direction = "Up";
                }
                break;
        }
    }

    /**
     * Function to move ghost depending on the direction of its
     * movement determined by changeDirection function
     * Changes speed of movement when inFrenzy to 2.5
     */
    @Override
    public void movement() {
        if (inFrenzy) {
            this.speed = 2.5;
        } else {
            this.speed = 3;
        }
        switch (direction) {
            case "Left":
                moveLeft();
                break;
            case "Right":
                moveRight();
                break;
            case "Up":
                moveUp();
                break;
            case "Down":
                moveDown();
                break;
        }
    }

    /**
     * Function that returns the direction of travel of pink ghost
     * @return current direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Based on the direction given a rectangle is created of the ghost
     * if they do travel in that direction
     * This is called in the Level function to see if the Ghost will hit a wall
     * if it continues in its direction of travel
     * @param direction
     * @return Rectangle with bounds of the ghost
     */

    public Rectangle getRectangle(String direction) {
        Point temp;
        switch (direction) {
            case "Left":
                temp = new Point(ghostCoord.x - image.getWidth() / 2 - speed,
                        ghostCoord.y - image.getHeight() / 2);
                return image.getBoundingBoxAt(temp);
            case "Right":
                temp = new Point(ghostCoord.x - image.getWidth() / 2 + speed,
                        ghostCoord.y - image.getHeight() / 2);
                return image.getBoundingBoxAt(temp);
            case "Up":
                temp = new Point(ghostCoord.x - image.getWidth() / 2,
                        ghostCoord.y - image.getHeight() / 2 - speed);
                return image.getBoundingBoxAt(temp);
            case "Down":
                temp = new Point(ghostCoord.x - image.getWidth() / 2,
                        ghostCoord.y - image.getHeight() / 2 + speed);
                return image.getBoundingBoxAt(temp);
        }
        return null;
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
