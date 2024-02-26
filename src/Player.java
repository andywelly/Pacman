import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Player class is what is moved by the arrow keys
 * The player can collide with various gameObjects and
 * various events occur
 * @author Andwele Ancheta
 */
public class Player implements MoveableObject {
    private Point position;
    private double speed = 3;
    private Image pacImage = new Image("res/pac.png");
    private Image pacOpenImage = new Image("res/pacOpen.png");
    private DrawOptions drawOptions = new DrawOptions();
    private boolean isOpen = true;
    private int lives = 3;
    private Point startingPos;
    private boolean inFrenzy = false;

    /**
     * Represents a player that is instantiated in the level class
     * Each level can only have one player.
     * @param position
     */
    public Player(Point position) {
        this.position = position;
        this.startingPos = position;
    }

    /**
     * public method to set player in Frenzy and increase speed
     */
    public void setInFrenzy() {
        inFrenzy = true;
        speed = 4;
    }

    /**
     * public method to stop frenzy and reduce speed
     */
    public void stopFrenzy() {
        inFrenzy = false;
        speed = 3;
    }

    /**
     * Draw method which changes image drawn every 15 frames
     */
    public void draw() {
        if (isOpen) {
            pacOpenImage.drawFromTopLeft(position.x, position.y, drawOptions);
        } else {
            pacImage.drawFromTopLeft(position.x, position.y, drawOptions);
        }
    }

    /**
     * Function to move player left
     */
    @Override
    public void moveLeft() {
        this.position = new Point(position.x - speed, position.y);
        drawOptions.setRotation(Math.PI);
    }
    /**
     * Function to move player right
     */
    @Override
    public void moveRight() {
        this.position = new Point(position.x + speed, position.y);
        drawOptions.setRotation(0);
    }
    /**
     * Function to move player up
     */
    @Override
    public void moveUp() {
        this.position = new Point(position.x, position.y - speed);
        drawOptions.setRotation(3 * Math.PI / 2);
    }
    /**
     * Function to move player down
     */
    @Override
    public void moveDown() {
        this.position = new Point(position.x, position.y + speed);
        drawOptions.setRotation(Math.PI / 2);
    }

    /**
     * Function that returns a rectangle based on the direciton the player is headed
     * This is called to check if a collision will occur if the player is move in a certain direciton
     * @param direction
     * @return rectangle of a player when they travel in the given direction
     */
    public Rectangle getRectangle(String direction) {
        Point temp;
        switch (direction) {
            case "Left":
                temp = new Point(position.x - pacImage.getWidth() / 2 - speed,
                        position.y - pacImage.getHeight() / 2);
                return pacImage.getBoundingBoxAt(temp);
            case "Right":
                temp = new Point(position.x - pacImage.getWidth() / 2 + speed,
                        position.y - pacImage.getHeight() / 2);
                return pacImage.getBoundingBoxAt(temp);
            case "Up":
                temp = new Point(position.x - pacImage.getWidth() / 2,
                        position.y - pacImage.getHeight() / 2 - speed);
                return pacImage.getBoundingBoxAt(temp);
            case "Down":
                temp = new Point(position.x - pacImage.getWidth() / 2,
                        position.y - pacImage.getHeight() / 2 + speed);
                return pacImage.getBoundingBoxAt(temp);
        }
        return null;
    }

    /**
     * getRectangle function with no paramenters returns the
     * rectangle of the player at the current position
     * @return rectangle of the player
     */
    public Rectangle getRectangle() {
        Point temp = new Point (position.x, position.y);
        return pacImage.getBoundingBoxAt(temp);
    }

    /**
     * changeImage is called in level class to
     * change pacImage from closed to open
     */
    public void changeImage() {
        if (isOpen == true) {
            isOpen = false;
        } else {
            isOpen = true;
        }
    }

    /**
     * when not in frenzy the player is moved back to starting position
     * and one life is taken away
     */
    public void ghostCollision() {
        if (!inFrenzy) {
            position = startingPos;
            lives--;
        }

    }

    /**
     * Getter method that returns the number of lives the player has
     * @return lives
     */
    public int getLives() {
        return lives;
    }
}