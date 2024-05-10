import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FlyingImp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlyingImp extends Enemy
{
    public FlyingImp() {
        super();
        this.health = 2;
        this.score = 15;
        this.speed = 3;
        this.acceleration = new Vector2D(0.0,0.0);
    }

    /**
     * Act - do whatever the FlyingImp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        takeDamage();
        if (health != 0) {
            move();    
        }
    }
    
    public void move() {
        PlayerShooter player = getWorld().getObjects(PlayerShooter.class).get(0);
        turnTowards(player.getX(), player.getY());
        move(3);
    }
}
