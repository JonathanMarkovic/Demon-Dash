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
        //Movement();
        //applyGravity();
        //checkForGround();
        //Jumping();
        //checkForShooting();
        takeDamage();
        if (this.isTouching(Gun.class)) {
            removeTouching(Gun.class);
            equipGun();
        }
        if (this.isTouching(Medkit.class)) {
            removeTouching(Medkit.class);
            //pickupMedkit();
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
    
    public void takeDamage() {
        if (this.isTouching(Enemy.class)) {
            health--;
            
            if (health == 0) {
                World world = new GameOverWorld();
                Greenfoot.setWorld(world);
            }
        }
    }
    
    /*public void pickupMedkit() {
        if (
    }*/
    private void Movement() 
    {
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) 
        {
            move(3);
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) 
        {
            move(-3);
        }
    }
    
    private void applyGravity() 
    {
        setLocation(getX(), getY() + verticalVelocity);
        verticalVelocity += gravity;
    }
    
    private void Jumping() 
    {
        if(Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) 
        {
            //System.out.println("Jumping key pressed"); //debugging
            jump();
        }
    }
    
    private boolean onGround() 
    {
        int groundHeight = getWorld().getHeight() - getImage().getHeight() / 2;
        return getY() >= groundHeight;
    }
    
    private void jump() 
    {
        if(onGround()) 
        {
            //System.out.println("Jumping"); //debugging
            verticalVelocity = -jumpStrength;
        }
    }
    
    private void checkForGround() 
    {
        if(getY() >= getWorld().getHeight() - getImage().getHeight() / 2) 
        {
            setLocation(getX(), getWorld().getHeight() - getImage().getHeight() / 2);
            verticalVelocity = 0;
        }
    }  
    
    /*
    private void checkForShooting() 
    {
      if (Greenfoot.mousePressed(null)) 
      {
        shooting = true;
      } 
      else if (Greenfoot.mouseClicked(null)) 
      {
        shooting = false;
        shootGun();
      }

      if (shooting) 
      {
          if (shootingTimer <= 0) 
          {
            shootGun();
            shootingTimer = shootingDelay;
          } 
          else 
          {
            shootingTimer--;
          }
      } 
      else 
      {
          shootingTimer = shootingDelay; // Reset the shooting timer when not shooting
      }  
    }
    
    public void shootGun() 
    {
        World world = getWorld();
        Bullet bullet = new Bullet();
        world.addObject(bullet, getX() , getY() ); 
    } 
    */
    
    
    
 }

