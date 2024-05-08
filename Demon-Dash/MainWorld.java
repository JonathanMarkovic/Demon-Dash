import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mainWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainWorld extends SimulationWorld
{
    public static int score = 0;
    private PlayerShooter playerShooter;
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
        {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        Greenfoot.playSound("demo Hellfire.wav");
        showText("Score: " + score, getWidth() / 2, 15);
        prepare();
    }
    
    public MainWorld(PlayerShooter player) {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        showText("Score: " + score, getWidth() / 2, 15);
        Greenfoot.playSound("demo Hellfire.wav");
        playerShooter = player;
        prepare2();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        playerShooter = new PlayerShooter();
        score = 0;
        addObject(playerShooter,17,571);
        Platform platform = new Platform();
        addObject(platform,228,505);
        Platform platform2 = new Platform();
        addObject(platform2,475,395);
        Platform platform3 = new Platform();
        addObject(platform3,393,465);
        Platform platform4 = new Platform();
        addObject(platform4,634,506);
        Platform platform5 = new Platform();
        addObject(platform5,207,333);
        Gun gun = new Gun();
        addObject(gun,331,561);
        gun.setLocation(122,568);
        Enemy enemy = new Enemy();
        addObject(enemy,638,473);
        Medkit medkit = new Medkit();
        addObject(medkit,393,441);
        AmmoBox ammoBox = new AmmoBox();
        addObject(ammoBox,228,476);
    }
    
    private void prepare2() {
        if (playerShooter != null)
        {
            addObject(playerShooter, 17, 571);
            if (playerShooter.gun != null)
            {
                addObject(playerShooter.gun,17,571);
            }
        }
            
        
        int platforms = Greenfoot.getRandomNumber(10);
        
        for (int i = 0; i < platforms; i++) {
            int x = Greenfoot.getRandomNumber(getWidth()) + 100;
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(new Platform(), x, y);
            
            if (Greenfoot.getRandomNumber(10) < 3) {
            addObject(new Enemy(), x, y - 20);
            }
        }
        
        if (Greenfoot.getRandomNumber(10) < 3) {
            addObject(new Enemy(), Greenfoot.getRandomNumber(getWidth()) + 100, Greenfoot.getRandomNumber(getHeight()));
        }
    }
    
    public static void calcScore(int amountAdded) {
        score += amountAdded;
    }
}

