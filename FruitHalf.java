import greenfoot.*;

public class FruitHalf extends Actor
{
    private int velocityY = -8;
    private int velocityX;
    private int gravity = 1;
    private int lifetime = 50; // DIKURANGI dari 60 ke 50
    
    public FruitHalf(GreenfootImage originalImage, boolean isLeft)
    {
        GreenfootImage half = new GreenfootImage(originalImage);
        half.scale(half.getWidth()/2, half.getHeight());
        setImage(half);
        
        if(isLeft)
        {
            velocityX = -Greenfoot.getRandomNumber(5) - 3;
        }
        else
        {
            velocityX = Greenfoot.getRandomNumber(5) + 3;
        }
    }
    
    public void act()
    {
        if(getWorld() == null) return;
        
        setLocation(getX() + velocityX, getY() + velocityY);
        velocityY += gravity;
        setRotation(getRotation() + 10);
        
        getImage().setTransparency(Math.max(0, getImage().getTransparency() - 5));
        
        lifetime--;
        
        // PERBAIKAN: Cek lebih ketat
        int worldHeight = getWorld().getHeight();
        int worldWidth = getWorld().getWidth();
        
        if(lifetime <= 0 || 
           getY() > worldHeight || 
           getY() < -30 ||
           getX() < -30 || 
           getX() > worldWidth + 30)
        {
            getWorld().removeObject(this);
        }
    }
}