import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingScreen extends SimulationWorld
{
    private int timer = 0;
    /**
     * Constructor for objects of class LoadingScreen.
     * 
     */
    public LoadingScreen()
    {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        prepare();
    }

    public void act() {
        timer++;
        int framesPerSecond = 55;
        if (timer == framesPerSecond * 4) {
            transitionToWorld(new Menu());
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
