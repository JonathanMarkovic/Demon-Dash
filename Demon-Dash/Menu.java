import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends SimulationWorld
{
    private int timer = 0;
    private static int highScore = 0;

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
    }
    
    public void act() {
        timer++;
        int framesPerSecond = 55;
        setHighScore();
        if (timer == framesPerSecond * 4) {
            transitionToWorld(new MainWorld());
        }
    }
    
    public void setHighScore() {
        Math.max(highScore, MainWorld.score);
    }
}
