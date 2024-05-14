import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Health extends Actor
{
    private GreenfootImage hp1 = new GreenfootImage("HPBar1.png");
    private GreenfootImage hp2 = new GreenfootImage("HPBar2.png");
    private GreenfootImage hp3 = new GreenfootImage("HPBar3.png");
    
    /**
     * Act - do whatever the Health wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkHealth();
    }
    
    public Health() {
        
    }
    
    public void checkHealth() {
        PlayerShooter player = getWorld().getObjects(PlayerShooter.class).get(0);
        
        int health = player.getHealth();
        if (health == 1) {
            setImage(hp1);
        } else if (health == 2) {
            setImage(hp2);
        } else if (health == 3) {
            setImage(hp3);
        }
    }
}
