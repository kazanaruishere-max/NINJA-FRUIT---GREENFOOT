import greenfoot.*;

public class LifeDisplay extends Actor
{
    public LifeDisplay()
    {
        updateLives(3);
    }
    
    public void updateLives(int lives)
    {
        GreenfootImage image = new GreenfootImage(120, 40);
        image.setColor(new Color(0, 0, 0, 150));
        image.fillRect(0, 0, 120, 40);
        
        for(int i = 0; i < lives; i++)
        {
            image.setColor(Color.RED);
            image.fillOval(10 + i*35, 10, 25, 25);
        }
        
        setImage(image);
    }
}