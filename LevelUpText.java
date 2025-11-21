import greenfoot.*;

public class LevelUpText extends Actor
{
    private int lifetime = 90;
    
    public LevelUpText(int level)
    {
        GreenfootImage image = new GreenfootImage(300, 100);
        image.setColor(new Color(255, 215, 0));
        image.setFont(new Font("Arial", true, false, 40));
        image.drawString("LEVEL " + level + "!", 20, 60);
        setImage(image);
    }
    
    public void act()
    {
        setLocation(getX(), getY() - 1);
        getImage().setTransparency(Math.max(0, getImage().getTransparency() - 3));
        
        lifetime--;
        if(lifetime <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}