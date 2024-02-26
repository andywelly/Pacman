import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
/**
 * A class for Pellet that is a GameObject
 * When the pellet is eaten Frenzy mode is entered
 * @author Andwele Ancheta
 */
public class Pellet extends GameObject implements Eatable {

    // There is only one pellet so different functions are needed
    private Point pelletCoord;
    private final int score = 0;
    private boolean isEaten = false;
    public Pellet(Point pelletCoord) {
        this.pelletCoord = pelletCoord;
        this.image = new Image("res/pellet.png");
    }

    /**
     * A different draw function is needed as there is only one pellet
     */
    @Override
    public void draw() {
        if (isEaten == false) {
            image.drawFromTopLeft(pelletCoord.x, pelletCoord.y);
        }
    }

    /**
     * A different collisions function is needed as there is only one pellet
     * @param playerRect, rectangle of player
     * @return boolean, true if a collision occurs
     */
    @Override
    public boolean collisions(Rectangle playerRect) {
        if (!isEaten) {
            Rectangle pellet = image.getBoundingBoxAt(pelletCoord);
            if (playerRect.intersects(pellet)) {
                isEaten = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method which returns points worth of pellet
     * @return score
     */
    @Override
    public int getScore() {
        return score;
    }


}
