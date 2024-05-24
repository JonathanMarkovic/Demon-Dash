import greenfoot.*;  
public class PlayerShooter extends Player
{   //Instance variables/Constructors
    private int gravity;
    private int jumpStrength;
    private int verticalVelocity;
    
    private boolean shooting = false;
    private int shootingDelay = 75; // Adjust the delay as needed
    private int shootingTimer = 0;
    private int health = 3;
    private boolean canTakeDamage = true;
    private double timer = 0.0;
    private int deathWallTimer = 0;
    
    public Gun gun;

    public PlayerShooter() 
    {
        gravity = 1; // Adjust gravity strength as needed
        jumpStrength = 15; // Adjust jump strength as needed
        verticalVelocity = 0;
    }

    public void act()
    { 
        super.act();
        timer += 0.02;
        if (timer > 1) {
            if (timer - 1 < 0.00005) {
                timer = 0;
            }
        }
        
        spawnDeathWall();
        takeDamage();
        pickupMedkit();
        dashing();
        if (this.isTouching(Gun.class)) {
            removeTouching(Gun.class);
            equipGun();
        }
       
        if (this.isTouching(AmmoBox.class)) {
            removeTouching(AmmoBox.class);
            //pickupAmmoBox();
        }
    }
    //Methods
    public void equipGun() {
        if (gun == null) {
            gun = new Gun();
            //gun.owner = this;
        }
        gun.owner = this;
        World world = getWorld();
        if(world != null) {
            world.addObject(gun, this.getX() + 5, this.getY());
        }
    }
    
    public void spawnDeathWall() {
        deathWallTimer++;
        DeathWall deathWall = new DeathWall();
        World world = getWorld();
        
        if (deathWallTimer == 55 * 8 && !containsDeathWall()) { 
            //55 frames per second, death wall will sapwn after around 5 seconds
            world.addObject(deathWall, deathWall.getImage().getWidth() / 2, world.getHeight() / 2);
            deathWallTimer = 0;
        }
    }
    
    public boolean containsDeathWall() {
        World world = getWorld();
        return (world.getObjects(DeathWall.class).size() > 0);
    }
    
    public void takeDamage() {
        if (timer == 0) {
                canTakeDamage = true;
            }
            
        if ((this.isTouching(Enemy.class) || isTouching(DeathWall.class)) && canTakeDamage) {
            health--;
            canTakeDamage = false;
            if (health == 0 || isTouching(DeathWall.class)) {
                World world = new GameOverWorld();
                Greenfoot.setWorld(world);
            }
        }
    }
    
    public void pickupMedkit() {
        if (isTouching(Medkit.class) && health < 3) {
            health++;
            removeTouching(Medkit.class);
        }
    }
   
    private void dashing() {
        if (Greenfoot.isKeyDown("Shift")) {
            if(Greenfoot.isKeyDown("D") || Greenfoot.isKeyDown("Right"))
            {
                canTakeDamage = false;
                move(15);
                canTakeDamage = true;
            } else if(Greenfoot.isKeyDown("A") || Greenfoot.isKeyDown("Left"))
            {
                canTakeDamage = false;
                move(-15);
                canTakeDamage = true;
            }
        }
    }
    
    public void onRightEdgeCollision() 
    {
        setLocation(getWorld().getWidth() - getImage().getWidth() / 2, getY());
            this.velocity = new Vector2D(0.0, this.velocity.getY());
            SimulationWorld world = (SimulationWorld) getWorld();
            world.transitionToWorld(new MainWorld(this));
            deathWallTimer = 0;
            MainWorld.calcScore(30);
    }
   
    public int getHealth() {
        return this.health;
    }
 }
 

