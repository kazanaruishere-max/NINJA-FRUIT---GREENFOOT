import greenfoot.*;

public class Explosion extends Actor
{
    private int size = 10;
    private int maxSize = 100;
    private int lifetime = 30;
    
    public Explosion()
    {
        updateImage();
    }
    
    public void act()
    {
        if(size < maxSize)
        {
            size += 8;
            updateImage();
        }
        
        getImage().setTransparency(Math.max(0, getImage().getTransparency() - 10));
        
        lifetime--;
        if(lifetime <= 0)
        {
            getWorld().removeObject(this);
        }
    }
    
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(size, size);
        image.setColor(new Color(255, 100, 0, 200));
        image.fillOval(0, 0, size, size);
        image.setColor(new Color(255, 200, 0, 180));
        image.fillOval(size/4, size/4, size/2, size/2);
        setImage(image);
    }
}