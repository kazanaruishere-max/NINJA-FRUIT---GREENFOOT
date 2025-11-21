import greenfoot.*;

public class Particle extends Actor
{
    private int velocityX;
    private int velocityY;
    private int lifetime = 25; // DIKURANGI dari 30 ke 25
    
    public Particle(Color color)
    {
        GreenfootImage image = new GreenfootImage(8, 8);
        image.setColor(color);
        image.fillOval(0, 0, 8, 8);
        setImage(image);
        
        velocityX = Greenfoot.getRandomNumber(11) - 5;
        velocityY = Greenfoot.getRandomNumber(11) - 5;
    }
    
    public void act()
    {
        if(getWorld() == null) return;
        
        setLocation(getX() + velocityX, getY() + velocityY);
        velocityY += 1;
        
        getImage().setTransparency(Math.max(0, getImage().getTransparency() - 10));
        
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