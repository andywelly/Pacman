# Pacman
Java Implementation of the Pac-Man game.
This project leverages BAGEL (Basic Academic Graphical Engine Library) and 
LWJGL (Lightweight Java Game Library) to recreate the beloved arcade game

## How to Play

Running the Game:
- Navigate to the 'src' folder
- Run the program using the 'ShadowPac' file
- Follow on-screen instructions to start playing

## Levels

Level 1:
- Ghosts remain stationary, providing a simple gameplay experience
- Collect all pellets to complete the level

Level 2:
- Ghosts move, increasing the challenge
- Pellets are introduced, allowing you to attack the ghosts
- Reach a score of 800 to complete the level

## Scoring System
- Dots - 10 points 
- Cherry - 10 Points 
- Ghosts - 30 Points 
- Pellets - 0 Points

## Ghost Movement
Each ghost implements different movement styles and speeds as described below, in order of complexity:

- Red Ghost: Moves horizontally back and forth
- Blue Ghost: Moves vertically back and forth
- Green Ghost: Randomly given initial movement direction and moves according to this
- Pink Ghost: Upon wall collision, randomly moves either vertically or horizontally


## Notes

LWGL may show an alert of Unsupported JNI version when using JAVA 19 or above
