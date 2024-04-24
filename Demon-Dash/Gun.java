import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends Actor
{
    public int gunOffset = 20;
    public Actor owner;
    public MouseInfo mouse = Greenfoot.getMouseInfo();
    
    /**
     * Act - do whatever the gun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        aimGun(); //To shoot bullet towards cursor (uses the findAngle to find cursor location n shoot n stuff)
    }
    
    public void aimGun() {
        int rotation = findAngle();
        
        setRotation(rotation); //set the rotation of the gun
        
        int x = owner.getX() + (int) (gunOffset * Math.cos(Math.toRadians(rotation))); // Set the gun location
        int y = owner.getY() + (int) (gunOffset * Math.sin(Math.toRadians(rotation))); // Set the gun location
        
        setLocation(x,y);
    }
        
    public int findAngle() //Angle of the cursor so player shoots towards cursor
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int mouseX = 0;
        int mouseY = 0;
        if (mouse != null) {
            mouseX = mouse.getX();
            mouseY = mouse.getY();
        }
        double deltaX = (mouseX  - this.getX());    //Change in X        (To calc the current coordinate of the mouse position & the coordinate of the object) 
        double deltaY = (mouseY - this.getY());     //Change in Y
        double angle = Math.atan2(deltaY, deltaX);    //Finds angle with deltaX and deltaY
        angle = Math.toDegrees(angle);
        int angle_return = (int)(angle);
        return angle_return;
    }    
}
