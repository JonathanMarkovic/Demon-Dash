import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slime extends Enemy
{   
    private GreenfootSound deathSound;
    public Slime() {
        super();
        this.health = 1;
        this.score = 5;
        this.speed = 1;
        this.deathSound = new GreenfootSound("slime fx.wav");
    }
    
    /**
     * Act - do whatever the Slime wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void takeDamage() 
    {
        if (isTouching(Bullet.class)) {
            health--;
            if (health == 0) {
                deathSound.play();
                getWorld().removeObject(this);
            }
        }
    }
}
