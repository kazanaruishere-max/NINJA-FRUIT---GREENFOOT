import greenfoot.*;

public class Bomb extends Actor
{
    private int velocityY = -15;
    private int velocityX;
    private int gravity = 1;
    private boolean exploded = false;
    private int actCounter = 0; // TAMBAHAN: Counter untuk safety
    
    public Bomb()
    {
        createImage();
        velocityX = Greenfoot.getRandomNumber(12) - 6;
        velocityY = -(Greenfoot.getRandomNumber(8) + 18);
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(50, 50);
        image.setColor(Color.BLACK);
        image.fillOval(10, 15, 30, 30);
        image.setColor(new Color(139, 69, 19));
        image.fillRect(23, 5, 4, 15);
        image.setColor(Color.ORANGE);
        image.fillOval(23, 3, 8, 8);
        setImage(image);
    }
    
    public void act()
    {
        if(!exploded && getWorld() != null)
        {
            actCounter++;
            move();
            checkBounds();
            
            // SAFETY: Hapus paksa jika sudah terlalu lama
            if(actCounter > 200)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    private void move()
    {
        setLocation(getX() + velocityX, getY() + velocityY);
        velocityY += gravity;
        setRotation(getRotation() + 5);
    }
    
    private void checkBounds()
    {
        World world = getWorld();
        if(world == null) return;
        
        int worldHeight = world.getHeight();
        int worldWidth = world.getWidth();
        
        // PERBAIKAN: Cek semua sisi dengan ketat
        // Hapus jika jatuh ke bawah (TANPA kurangi nyawa)
        if(getY() >= worldHeight - 10)
        {
            world.removeObject(this);
            return;
        }
        
        // Hapus jika keluar dari sisi kiri, kanan, atau atas
        if(getX() <= 0 || getX() >= worldWidth || getY() <= -20)
        {
            world.removeObject(this);
            return;
        }
    }
    
    public void explode()
    {
        if(!exploded && getWorld() != null)
        {
            exploded = true;
            
            int currentX = getX();
            int currentY = getY();
            World world = getWorld();
            
            createExplosion(currentX, currentY, world);
            
            MyWorld myWorld = (MyWorld)world;
            if(!myWorld.isGameOver())
            {
                myWorld.loseLife();
            }
            
            if(getWorld() != null)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    private void createExplosion(int x, int y, World world)
    {
        if(world != null)
        {
            Explosion explosion = new Explosion();
            world.addObject(explosion, x, y);
        }
    }
}