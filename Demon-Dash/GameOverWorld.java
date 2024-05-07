import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends SimulationWorld
{

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld(int score)
    {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        showText("Game Over", getWidth() / 2, getHeight() / 2);
        showText(score + "", getWidth() / 2, getHeight() / 4); 
    }
    
    public GameOverWorld() {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        showText("Game Over", getWidth() / 2, getHeight() / 2);
    }
}
