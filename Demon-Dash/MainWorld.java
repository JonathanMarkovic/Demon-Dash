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
        Platform platform = new Platform();
        addObject(platform,228,505);
        Platform platform2 = new Platform();
        addObject(platform2,475,395);
        Platform platform3 = new Platform();
        addObject(platform3,393,465);
        Platform platform4 = new Platform();
        addObject(platform4,634,506);
        Platform platform5 = new Platform();
        addObject(platform5,207,333);
        Gun gun = new Gun();
        addObject(gun,331,561);
    }
}

