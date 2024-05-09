import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Player
{
    private int health = 3;
    private int score = 10;
    private int enemySpeed = 2;
    
    public Enemy() {
        super(); 
        this.acceleration = new Vector2D(0.0,-10.0);
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        eulerIntegration();
        
        checkCollision();
        takeDamage(); 
        if (health != 0) {
            move();
        }
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
        PlayerShooter player = getWorld().getObjects(PlayerShooter.class).get(0);
        int x = player.getX();
        int y = player.getY();
        int movement = 0;
        
        if (getX() - x < 0) {
            movement = -1;
        } else if (getX() - x > 0) {
            movement = 1;
        } else if (getX() - x == 0) {
            movement = 0;
        }
        
        if (movement < 0) 
       { 
            this.velocity = new Vector2D(enemySpeed, this.velocity.getY());   
       }
       
       else if (movement > 0) 
       { 
            this.velocity = new Vector2D(-enemySpeed, this.velocity.getY());      
       }
       else if (movement == 0)
       {
           this.velocity = new Vector2D(0, this.velocity.getY());  
       }
    }
}
