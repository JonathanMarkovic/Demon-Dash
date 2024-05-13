import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slime extends Enemy
{   
    GifImage slime;
    GreenfootImage myImage;
    
    public Slime() {
        super();
        slime = new GifImage("Slime.gif");
        this.health = 1;
        this.score = 5;
        this.speed = 1;
    }
    
    /**
     * Act - do whatever the Slime wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        myImage = slime.getCurrentImage();
        setImage(myImage);
    }
}
