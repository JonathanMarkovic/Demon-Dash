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
    private int timer = 0;
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
        {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        showTextWithFont("RUN", getWidth() / 2 - 100, getHeight() / 2 - 50);
        Greenfoot.playSound("demo Hellfire.wav");
        music = new GreenfootSound("demo Hellfire.wav");
        prepare();
    }
    
    public MainWorld(PlayerShooter player) {
        super("", 800, 600, new Point2D(0.0, 0.0), 20);
        showText("Score: " + score, getWidth() / 2, 15);
        music = new GreenfootSound("demo Hellfire.wav");
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
        AmmoCounter ammoCounter = new AmmoCounter();
        addObject(ammoCounter,23,21);
        Slime slime = new Slime();
        addObject(slime,745,579);
        FlyingImp flyingImp = new FlyingImp();
        addObject(flyingImp,473,162);
        Health health = new Health();
        addObject(health,758,29);
        health.setLocation(737,27);
    }
    
    private void prepare2() {
        Health health = new Health();
        addObject(health,758,29);
        health.setLocation(737,27);
        
        if (Greenfoot.getRandomNumber(10) >= 5) {
            Medkit medkit = new Medkit();
            int x = Greenfoot.getRandomNumber(800);
            int y = Greenfoot.getRandomNumber(100) + 500;
            addObject(medkit, x, y);
            addObject(new Platform(), x + Greenfoot.getRandomNumber(100), y + Greenfoot.getRandomNumber(50));
        }
        
        if (Greenfoot.getRandomNumber(10) >= 4) {
            AmmoBox ammoBox = new AmmoBox();
            int x = Greenfoot.getRandomNumber(800);
            int y = Greenfoot.getRandomNumber(100) + 500;
            addObject(ammoBox, x, y);
            addObject(new Platform(), x + Greenfoot.getRandomNumber(100), y + Greenfoot.getRandomNumber(50));
        }
        
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
            int x = Greenfoot.getRandomNumber(getWidth()) + 150;
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(new Platform(), x, y);
            int randomEnemySpawn = Greenfoot.getRandomNumber(100);
            int ifEnemySpawn = Greenfoot.getRandomNumber(10);
            if (ifEnemySpawn <= 4) {
                if (randomEnemySpawn < 10) {
                    addObject(new FlyingImp(), x, 50);
                } else if (randomEnemySpawn < 50){
                    addObject(new Slime(), x, y - 20);
                    addObject(new Slime(), x + 15, y - 30);
                    addObject(new Slime(), x + 20, y - 30);
                } else if (randomEnemySpawn < 70) {
                    addObject(new Enemy(), x, y - 20);
                }
            }
        }
        
        if (Greenfoot.getRandomNumber(10) < 3) {
            addObject(new Enemy(), Greenfoot.getRandomNumber(getWidth()) + 100, Greenfoot.getRandomNumber(getHeight()));
        }
    }
    
    public static void calcScore(int amountAdded) {
        score += amountAdded;
    }
    
    public void showTextWithFont(String str, int x, int y) {
        GreenfootImage img = getBackground();
        Font font = new Font("Britannic Bold", false, false, 100);
        img.setFont(font);
        img.setColor(Color.RED); // ORANGE RED YELLOW
        img.drawString(str, x, y);
    }
}

