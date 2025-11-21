import greenfoot.*;

public class Watermelon extends Fruit
{
    public Watermelon()
    {
        super();
        createImage();
        points = 20;
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(80, 80);
        image.setColor(new Color(34, 139, 34));
        image.fillOval(5, 5, 70, 70);
        image.setColor(Color.BLACK);
        for(int i = 0; i < 5; i++)
        {
            image.fillOval(15 + i*10, 20 + i*8, 5, 5);
        }
        setImage(image);
    }
    
    protected Color getFruitColor()
    {
        return new Color(255, 105, 180);
    }
}