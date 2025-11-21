import greenfoot.*;

public class SlashEffect extends Actor
{
    private int lifetime = 10;
    
    public SlashEffect()
    {
        GreenfootImage image = new GreenfootImage(30, 30);
        image.setColor(new Color(255, 255, 255, 150));
        image.fillOval(0, 0, 30, 30);
        setImage(image);
    }
    
    public void act()
    {
        getImage().setTransparency(getImage().getTransparency() - 25);
        
        lifetime--;
        if(lifetime <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}