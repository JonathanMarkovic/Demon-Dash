import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FlyingImp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlyingImp extends Enemy
{
    private GifImage skull;
    private GreenfootImage myImage;
    private GreenfootSound sound = new GreenfootSound("imp fx.wav");
    
    public FlyingImp() {
        super();
        skull = new GifImage("skull.gif");
        this.health = 1;
        this.score = 15;
        this.speed = 1;
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
            myImage = skull.getCurrentImage();
            myImage = myImage;
            setImage(myImage);
        }
    }
    
    public void move() {
        PlayerShooter player = getWorld().getObjects(PlayerShooter.class).get(0);
        int x = player.getX();
        int y = player.getY();
        int xMove = (getX() <= x) ? 1 : -1;
        int yMove = (getY() <= y) ? 1 : -1;
        setLocation(getX() + xMove * speed, getY() + yMove * speed);
        sound.play();
        if (this.health == 0) {
            sound.stop();
        }
    }
}
