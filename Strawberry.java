import greenfoot.*;

public class Strawberry extends Fruit
{
    public Strawberry()
    {
        super();
        createImage();
        points = 18;
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(50, 55);
        image.setColor(new Color(220, 20, 60));
        int[] xPoints = {25, 10, 15, 25, 35, 40};
        int[] yPoints = {10, 25, 45, 50, 45, 25};
        image.setColor(new Color(220, 20, 60));
        image.fillPolygon(xPoints, yPoints, 6);
        image.setColor(Color.GREEN);
        image.fillOval(20, 5, 10, 8);
        setImage(image);
    }
    
    protected Color getFruitColor()
    {
        return new Color(220, 20, 60);
    }
}