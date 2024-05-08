import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends SimulationActor
{
    private int health = 3;
    private int score = 10;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        takeDamage();
        
    }
    
    public void takeDamage() {
        if (this.isTouching(Bullet.class)) {
            removeTouching(Bullet.class);
            health--;
            if (health == 0) {
                MainWorld.calcScore(score);
                getWorld().removeObject(this);
            }
        }
    }
    
    public void move() {
        
    }
}
