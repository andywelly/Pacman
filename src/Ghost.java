import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * An abstract class for Ghost which are MoveableObject
 * Ghosts are Eatable on collision with the player
 * @author Andwele Ancheta
 */
public abstract class Ghost implements MoveableObject, Eatable {

    // ghostImage is used for the size of the ghost
    private Image ghostImage = new Image("res/ghostRed.png");
    private static final Image frenzyImage = new Image("res/ghostFrenzy.png");
    /**
     * These are protected as they will have to
     * be accessed and changed in each class
     */
    protected Point ghostCoord;
    protected boolean initialDirection = true;
    protected boolean inFrenzy;
    protected boolean isEaten = false;
    protected double speed;

    protected Image image;

    /**
     * Method to check if Ghost collides with the player
     * @param playerRect
     * @return boolean, true if a collision occurs
     */
    public boolean collisions(Rectangle playerRect) {
        Rectangle ghost = ghostImage.getBoundingBoxAt(ghostCoord);
        if (playerRect.intersects(ghost)) {
            if (inFrenzy) {
                isEaten = true;
            }
            return true;
        }
        return false;
    }

    /**
     * Draw function checks if the ghost has been eaten and if it is in frenzy
     */
    public void draw() {
        if (!isEaten) {
            if (inFrenzy) {
                frenzyImage.drawFromTopLeft(ghostCoord.x, ghostCoord.y);
            } else {
                image.drawFromTopLeft(ghostCoord.x, ghostCoord.y);
            }
        }
    }

    /**
     * Function to create a Rectangle the size of the Ghost
     * To use in collision detection
     * @return Rectangle of the Ghost
     */
    public Rectangle getRectangle() {
        Point temp = new Point (ghostCoord.x - ghostImage.getWidth() / 2,
                ghostCoord.y - ghostImage.getHeight() / 2);
        return ghostImage.getBoundingBoxAt(temp);
    }


    /**
     * movement function to be implemented in each Ghost Class
     * Differnent ghosts will move differently
     */
    public abstract void movement();


    /**
     * moveLeft function moves ghost left
     */
    @Override
    public void moveLeft() {
        this.ghostCoord = new Point(ghostCoord.x - speed, ghostCoord.y);
    }

    /**
     * moveRight function moves ghost right
     */
    @Override
    public void moveRight() {
        this.ghostCoord = new Point(ghostCoord.x + speed, ghostCoord.y);
    }

    /**
     * moveUp function moves ghost up
     */
    @Override
    public void moveUp() {
        this.ghostCoord = new Point(ghostCoord.x, ghostCoord.y - speed);
    }

    /**
     * moveDown function moves ghost down
     */
    @Override
    public void moveDown() {
        this.ghostCoord = new Point(ghostCoord.x, ghostCoord.y + speed);
    }


    /**
     * changeDirection changes the direction of movement to the opposite
     * It is called in some Ghost movement function
     */
    public void changeDirection() {
        if (initialDirection) {
            initialDirection = false;
        } else {
            initialDirection = true;
        }
    }

    /**
     * Function to set the inFrenzy boolean value to true
     */
    public void setInFrenzy() {
        inFrenzy = true;
    }

    /**
     * Function to end frenzy and reset the Ghosts being eaten
     */
    public void stopFrenzy() {
        inFrenzy = false;
        isEaten = false;
    }
}
