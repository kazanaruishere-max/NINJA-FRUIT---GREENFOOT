import greenfoot.*;

public class Orange extends Fruit
{
    public Orange()
    {
        super();
        createImage();
        points = 15;
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(55, 55);
        image.setColor(Color.ORANGE);
        image.fillOval(10, 10, 35, 35);
        setImage(image);
    }
    
    protected Color getFruitColor()
    {
        return Color.ORANGE;
    }
}