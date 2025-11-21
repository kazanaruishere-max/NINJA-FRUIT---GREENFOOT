import greenfoot.*;

public abstract class Fruit extends Actor
{
    protected int velocityY = -15;
    protected int velocityX;
    protected int gravity = 1;
    protected boolean sliced = false;
    protected int points = 10;
    private int actCounter = 0; // TAMBAHAN: Counter untuk safety
    
    public Fruit()
    {
        velocityX = Greenfoot.getRandomNumber(12) - 6;
        velocityY = -(Greenfoot.getRandomNumber(8) + 18);
    }
    
    public void act()
    {
        if(!sliced && getWorld() != null)
        {
            actCounter++;
            move();
            checkBounds();
            
            // SAFETY: Hapus paksa jika sudah terlalu lama (200 acts = ~3 detik)
            if(actCounter > 200)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    protected void move()
    {
        setLocation(getX() + velocityX, getY() + velocityY);
        velocityY += gravity;
        setRotation(getRotation() + 5);
    }
    
    protected void checkBounds()
    {
        World world = getWorld();
        if(world == null) return;
        
        int worldHeight = world.getHeight();
        int worldWidth = world.getWidth();
        
        // PERBAIKAN: Cek semua sisi dengan margin lebih besar
        // Hapus jika Y > tinggi layar (jatuh ke bawah)
        if(getY() >= worldHeight - 10)
        {
            MyWorld myWorld = (MyWorld)world;
            if(!myWorld.isGameOver())
            {
                myWorld.loseLife();
            }
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
    
    public void slice()
    {
        if(!sliced && getWorld() != null)
        {
            sliced = true;
            MyWorld world = (MyWorld)getWorld();
            world.addScore(points);
            
            createSliceEffect();
            
            if(getWorld() != null)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    protected void createSliceEffect()
    {
        World world = getWorld();
        if(world == null) return;
        
        int currentX = getX();
        int currentY = getY();
        
        FruitHalf half1 = new FruitHalf(getImage(), true);
        FruitHalf half2 = new FruitHalf(getImage(), false);
        
        world.addObject(half1, currentX - 10, currentY);
        world.addObject(half2, currentX + 10, currentY);
        
        for(int i = 0; i < 8; i++)
        {
            Particle particle = new Particle(getFruitColor());
            world.addObject(particle, currentX, currentY);
        }
    }
    
    protected abstract Color getFruitColor();
}