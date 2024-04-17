import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mainWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mainWorld extends Scroll
{

    /**
     * Constructor for objects of class mainWorld.
     * 
     */
    public mainWorld()
        {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1100, 700, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,136,655);
    }
}

