import greenfoot.*;

public class Apple extends Fruit
{
    public Apple()
    {
        super();
        createImage();
        points = 10;
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(60, 60);
        image.setColor(Color.RED);
        image.fillOval(10, 10, 40, 40);
        image.setColor(new Color(139, 69, 19));
        image.fillRect(28, 5, 4, 12);
        setImage(image);
    }
    
    protected Color getFruitColor()
    {
        return Color.RED;
    }
}