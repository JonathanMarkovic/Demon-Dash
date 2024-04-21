import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends Bullet
{
    private int gunOffset = 5;
    public Actor owner;
    
    /**
     * Act - do whatever the gun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (mouse != null) {
            aimGun();
        }
    }
    
    public void aimGun() {
        int rotation = findAngle();
        
        setRotation(rotation); //set the rotation of the gun
        
        int x = owner.getX() + (int) (gunOffset * Math.cos(Math.toRadians(rotation))); // Set the sword location
        int y = owner.getY() + (int) (gunOffset * Math.sin(Math.toRadians(rotation))); // Set the sword location
        
        setLocation(x,y);
    }
}
