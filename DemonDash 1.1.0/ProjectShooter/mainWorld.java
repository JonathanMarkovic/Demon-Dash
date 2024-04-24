import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mainWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainWorld extends SimulationWorld
{

    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
        {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        PlayerShooter playerShooter = new PlayerShooter();
        addObject(playerShooter,17,571);
    }
}

