import greenfoot.*;  

/**
 * Write a description of class bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Gun
{
    //Instance variables
    public boolean created = false;
    public MouseInfo mouse = Greenfoot.getMouseInfo();
    
    public void act()
    {
        /*if (!created)   
        {
            setRotation(findAngle()); //To shoot bullet towards cursor (uses the findAngle to find cursor location n shoot n stuff)
        }
        created = true; //So it doesn't stop at the cursor's location
        move(25);*/
        shoot();
        ifAtWorldEdge();
    }
    
    public void shoot() {
        if (!created)   
        {
            int rotation = findAngle();
            
            int x = super.getX() + (int) ((super.getImage().getWidth() / 2) * Math.cos(Math.toRadians(rotation))); // Set the bullet location
            int y = super.getY() + (int) ((super.getImage().getWidth() / 2) * Math.sin(Math.toRadians(rotation))); // Set the bullet location
            
            setLocation(x,y);
            setRotation(rotation); //To rotate bullet towards cursor
        }
        created = true; //So it doesn't stop at the cursor's location
        move(20);
    }
    
    /*
    public int findAngle() //Angle of the cursor so player shoots towards cursor
    {
        int mouseX = mouse.getX();
        int mouseY = mouse.getY();
        double deltaX = (mouseX  - this.getX());    //Change in X        (To calc the current coordinate of the mouse position & the coordinate of the object) 
        double deltaY = (mouseY - this.getY());     //Change in Y
        double angle = Math.atan2(deltaY, deltaX);    //Finds angle with deltaX and deltaY
        angle = Math.toDegrees(angle);
        int angle_return = (int)(angle);
        return angle_return;
    }
    */
    public void ifAtWorldEdge() //may change this to wall object later on
    {
        if (isAtEdge())
        {
             getWorld().removeObject(this);
        }
    }
}
