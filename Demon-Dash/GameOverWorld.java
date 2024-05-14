import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends SimulationWorld
{
    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        showTextWithFont("Game Over", getWidth() / 4, getHeight() / 2 - 50);
        showTextWithFont("Score: " + MainWorld.score, getWidth() / 4, getHeight() / 2 + 250);
        music = new GreenfootSound("Game Over.wav");
    }
    
    public void showTextWithFont(String str, int x, int y) {
        GreenfootImage img = getBackground();
        Font font = new Font("Britannic Bold", false, false, 100);
        img.setFont(font);
        img.setColor(Color.RED); // ORANGE RED YELLOW
        img.drawString(str, x, y);
    }
}
