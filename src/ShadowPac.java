import bagel.*;

import java.io.*;

public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 200;
    private final static int OFFSET_X = 60;
    private final static int OFFSET_Y = 190;
    private final static int MESSAGE_X = 200;
    private final static int MESSAGE_Y = 350;
    private final static int TITLE_FONT_SIZE = 64;
    private final static int MESSAGE_FONT_SIZE = 40;
    private final static int INSTRUCTION_FONT_SIZE = 24;

    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");
    private Level levelZero = new Level(new File("res/level0.csv"), 1210);
    private Level levelOne = new Level(new File("res/level1.csv"), 800);
    private final Font TITLE_FONT = new Font("res/FSO8BITR.TTF", TITLE_FONT_SIZE);
    private final Font MESSAGE_FONT = new Font("res/FSO8BITR.TTF", MESSAGE_FONT_SIZE);
    private final Font INSTRUCTION_FONT = new Font("res/FSO8BITR.TTF", INSTRUCTION_FONT_SIZE);
    /**
     * gameState 0 -> Title Screen
     * gameState 1 -> Level0
     * gameState 2 -> Level Complete Screen
     * gameState 3 -> Level1
     * gameState 4 -> Win Screen
     * gameState 5 -> Lose Screen
     */
    private static int gameState = 3;
    private int frameCount = 0;

    /**
     * ShadowPac calls constructor from AbstractGame
     * readCSV is called when the game is created
     */
    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        readCSV();
    }

    /**
     * Method used to read file and create objects in each level.
     */
    private void readCSV() {
        levelZero.readCSV();
        levelOne.readCSV();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * Method for showing title screen of the game
     */
    private void titleScreen() {
        TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
        INSTRUCTION_FONT.drawString(" PRESS SPACE TO START\n" +
                "USE ARROW KEYS TO MOVE\n\n"
                , TITLE_X + OFFSET_X, TITLE_Y + OFFSET_Y);

        INSTRUCTION_FONT.drawString("CLEAR ALL PELLETS TO MOVE TO STAGE 2\n" +
                        "WHERE THE GHOSTS WILL START MOVING",
                TITLE_X - OFFSET_X, TITLE_Y + 2 * OFFSET_Y);

    }

    /**
     * Method to show when the first level is complete
     */
    private void levelComplete() {
        frameCount ++;
        if (frameCount < 200) {
            TITLE_FONT.drawString("LEVEL COMPLETE!", MESSAGE_X, MESSAGE_Y);
        } else {
            MESSAGE_FONT.drawString("  PRESS SPACE TO START\n" +
                    " USE ARROW KEYS TO MOVE\n" +
                    "EAT THE PELLET TO ATTACK", MESSAGE_X, MESSAGE_Y);
        }

    }

    /**
     * Methods to show win screen when the player has won
     */
    private void winScreen() {
        TITLE_FONT.drawString("WELL DONE!", TITLE_X, TITLE_Y);
    }

    /**
     * Method to show lose screen when player has lost
     */
    private void loseScreen() {
        TITLE_FONT.drawString("GAME OVER!", TITLE_X, TITLE_Y);
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     * @param input
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        if (input.wasPressed(Keys.SPACE)){
            if (gameState == 0) {
                gameState = 1;
            } else if (gameState == 2) {
                gameState = 3;
            }
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // Depending on the gameState different screens are shown
        //gameState 0 is the title Screen
        if (gameState == 0) {
            titleScreen();

        // gameState 1 is level 0
        } else if (gameState == 1) {
            levelZero.draw();
            levelZero.update(input);
            if (levelZero.isCompleted()) {
                gameState = 2;
            }
            if (levelZero.isFailed()) {
                gameState = 5;
            }
        // gameState 2 is the level0 complete screen
        } else if (gameState == 2) {
            levelComplete();
        // gameState 3 is level1
        } else if (gameState == 3) {
            levelOne.draw();
            levelOne.update(input);
            if (levelOne.isCompleted()) {
                gameState = 4;
            }
            if (levelOne.isFailed()) {
                gameState = 5;
            }
        // gameState 4 is the win screen
        } else if (gameState == 4) {
            winScreen();
        // gameState 5 is the loss screen
        } else if (gameState == 5) {
            loseScreen();
        }

    }
}
