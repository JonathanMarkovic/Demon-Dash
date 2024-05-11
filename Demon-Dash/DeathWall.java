import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathWall extends Actor
{
    /**
     * Act - do whatever the DeathWall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(2);
        addDeathWall();
    }
    
    public void addDeathWall() {
        World world = getWorld();
        if (getX() == getImage().getWidth() /2 * 3) {
            world.addObject(new DeathWall(), getImage().getWidth() / 2, world.getHeight() / 2);
        }
    }
}
