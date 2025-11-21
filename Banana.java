import greenfoot.*;

public class Banana extends Fruit
{
    public Banana()
    {
        super();
        createImage();
        points = 12;
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(70, 40);
        image.setColor(Color.YELLOW);
        image.fillOval(10, 5, 50, 30);
        image.setColor(new Color(139, 69, 19));
        image.fillRect(8, 15, 8, 5);
        setImage(image);
    }
    
    protected Color getFruitColor()
    {
        return Color.YELLOW;
    }
}