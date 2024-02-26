import bagel.Font;
import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A class for a Level which is instantiated in ShadowPac
 * The file is read and the game objects are made in this class
 * @author Andwele Ancheta
 */
public class Level {
    private File file;
    private Player player;
    private GhostStationary GhostStationary;
    private Walls walls;
    private Dots dots;
    private Cherry cherry;
    private Pellet pellet;
    private Point playerCoords;
    private GhostBlue ghostBlue;
    private GhostGreen ghostGreen;
    private GhostRed ghostRed;
    private GhostPink ghostPink;
    private List<Point> ghostCoords = new ArrayList<>();
    private List<Point> wallCoords = new ArrayList<>();
    private List<Point> dotCoords = new ArrayList<>();
    private List<Point> cherryCoords = new ArrayList<>();
    private Image heartImage = new Image ("res/heart.png");
    private final Font font20 = new Font("res/FSO8BITR.TTF", 20);
    private int frameCount = 0;
    private int score = 0;
    private int targetScore;

    /**
     * Represents a level that can be created in the ShadowPac game
     * There can be many levels within the game
     * @param file
     * @param targetScore
     */
    public Level(File file, int targetScore) {
        this.file = file;
        this.targetScore = targetScore;
    }

    /**
     * Calls all the draw functions from each object
     */
    public void draw() {
        // These objects cannot be null so they are immediately drawn
        player.draw();
        walls.draw();
        dots.draw();

        // Draws objects if they are not null as some levels may not have it
        if (GhostStationary != null) {
            GhostStationary.draw();
        }
        if (ghostBlue != null) {
            ghostBlue.draw();
        }
        if (ghostGreen != null) {
            ghostGreen.draw();
        }
        if (ghostPink != null) {
            ghostPink.draw();
        }
        if (ghostRed != null) {
            ghostRed.draw();
        }

        if (cherry != null) {
            cherry.draw();
        }
        if (pellet != null) {
            pellet.draw();
        }

    }

    /**
     * Checks the number of frames that have passed to see
     * whether to stop frenzy and change player Image
     */
    public void checkFrames() {
        if (frameCount % 15 == 0) {
            player.changeImage();
        }
        frameCount ++;
        // Frenzy lasts 1000 frames, frameCount is reset once frenzy starts
        if (frameCount == 1000) {
            stopFrenzy();
        }
    }

    /**
     * stopFrenzy to call stopFrenzy method from all objects
     * checkFrames calls method once 1000 frames has passed
     */
    public void stopFrenzy() {
        player.stopFrenzy();
        if (ghostBlue != null && ghostGreen != null && ghostRed != null && ghostPink != null) {
            ghostRed.stopFrenzy();
            ghostBlue.stopFrenzy();
            ghostGreen.stopFrenzy();
            ghostPink.stopFrenzy();
        }
    }

    /**
     * checkCollisions to call collisions method from all objects
     * Once pellet has been eaten objects are also setInFrenzy
     */
    private void checkCollisions() {
        if (dots != null && dots.collisions(player.getRectangle())) {
            score += dots.getScore();
        }
        if (cherry != null && cherry.collisions(player.getRectangle())) {
            score += cherry.getScore();
        }
        if (pellet != null && pellet.collisions(player.getRectangle())) {
            frameCount = 0;
            player.setInFrenzy();
            ghostRed.setInFrenzy();
            ghostBlue.setInFrenzy();
            ghostGreen.setInFrenzy();
            ghostPink.setInFrenzy();
        }


        if (GhostStationary != null && GhostStationary.collisions(player.getRectangle())) {
            player.ghostCollision();
        }

        if (ghostRed != null && ghostRed.collisions(player.getRectangle())) {
            player.ghostCollision();
            score += ghostRed.getScore();
        }
        if (ghostBlue != null && ghostBlue.collisions(player.getRectangle())) {
            player.ghostCollision();
            score += ghostBlue.getScore();
        }
        if (ghostGreen != null && ghostGreen.collisions(player.getRectangle())) {
            player.ghostCollision();
        }
        if (ghostPink != null && ghostPink.collisions(player.getRectangle())) {
            player.ghostCollision();
        }


    }


    /**
     * ghostMovement calls the movement functions in each of the moving ghosts
     */
    private void ghostMovement() {
        if (ghostBlue != null) {
            ghostBlue.movement();
            if (walls.collisions(ghostBlue.getRectangle())) {
                ghostBlue.changeDirection();
            }
        }
        if (ghostGreen != null) {
            ghostGreen.movement();
            if (walls.collisions(ghostGreen.getRectangle())) {
                ghostGreen.changeDirection();
            }
        }
        if (ghostRed != null) {
            ghostRed.movement();
            if (walls.collisions(ghostRed.getRectangle())) {
                ghostRed.changeDirection();
            }
        }
        if (ghostPink != null) {
            ghostPink.movement();
            if (walls.collisions(ghostPink.getRectangle(ghostPink.getDirection()))) {
                ghostPink.changeDirection();
            }
        }

    }

    /**
     * Update method calls movement functions and collisions, checkFrames and ghostMovement
     *
     * @param input
     */
    public void update(Input input) {
        /**
         * Movement
         */
        if (input.isDown(Keys.LEFT) && !walls.collisions(player.getRectangle("Left"))) {
            player.moveLeft();
        } else if (input.isDown(Keys.RIGHT) && !walls.collisions(player.getRectangle("Right"))) {
            player.moveRight();
        } else if (input.isDown(Keys.UP) && !walls.collisions(player.getRectangle("Up"))) {
            player.moveUp();
        } else if (input.isDown(Keys.DOWN) && !walls.collisions(player.getRectangle("Down"))) {
            player.moveDown();
        }


        checkCollisions();


        // Draws score and Hearts based on lives
        font20.drawString("SCORE " + score, 25, 25);
        for (int i = 0; i < player.getLives(); i ++) {
            heartImage.drawFromTopLeft(900 + (30 * i), 10);
        }

        checkFrames();
        ghostMovement();

    }

    /**
     * readCSV reads the given CSV file and adds coordinates to ArrayLists and creates objects
     * readCSV called by ShadowPac
     */
    public void readCSV() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineInfo = line.split(",");
                String type = lineInfo[0];
                int x = Integer.parseInt(lineInfo[1]);
                int y = Integer.parseInt(lineInfo[2]);
                switch (type) {
                    case "Player":
                        playerCoords = new Point(x, y);
                        break;
                    case "Ghost":
                        ghostCoords.add(new Point(x, y));
                        break;
                    case "Wall":
                        wallCoords.add(new Point(x, y));
                        break;
                    case "Dot":
                        dotCoords.add(new Point(x, y));
                        break;

                    case "Cherry":
                        cherryCoords.add(new Point(x, y));
                        break;
                    case "Pellet":
                        pellet = new Pellet(new Point(x, y));
                        break;
                    case "GhostRed":
                        ghostRed = new GhostRed(new Point(x, y));
                        break;
                    case "GhostBlue":
                        ghostBlue = new GhostBlue(new Point(x, y));
                        break;
                    case "GhostGreen":
                        ghostGreen = new GhostGreen(new Point(x, y));
                        break;
                    case "GhostPink":
                        ghostPink = new GhostPink(new Point(x, y));
                }
            }
            player = new Player(playerCoords);
            GhostStationary = new GhostStationary(ghostCoords);
            walls = new Walls(wallCoords);
            dots = new Dots(dotCoords);
            cherry = new Cherry(cherryCoords);
            scanner.close();
            /** catch block if there is no csv file **/
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Checks to see if Player has reached target score
     * @return boolean returns true if target score has been reached, false if not
     */
    public boolean isCompleted() {
        if (score >= targetScore) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if player still has lives
     * @return returns true if player has lost all lives, false if not
     */
    public boolean isFailed() {
        if (player.getLives() == 0) {
            return true;
        }
        return false;
    }

}
