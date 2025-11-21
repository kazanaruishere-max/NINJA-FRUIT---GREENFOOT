import greenfoot.*;

public class Sword extends Actor
{
    private int slashDuration = 0;
    private int lastX, lastY;
    
    public Sword()
    {
        GreenfootImage image = new GreenfootImage(40, 40);
        image.setColor(Color.RED);
        image.fillOval(0, 0, 40, 40);
        setImage(image);
    }
    
    public void act()
    {
        followMouse();
        createSlashEffect();
        checkFruitCollision();
    }
    
    private void followMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            lastX = getX();
            lastY = getY();
            setLocation(mouse.getX(), mouse.getY());
        }
    }
    
    private void createSlashEffect()
    {
        if(Greenfoot.mouseDragged(null) && getWorld() != null)
        {
            SlashEffect slash = new SlashEffect();
            getWorld().addObject(slash, getX(), getY());
        }
    }
    
    private void checkFruitCollision()
    {
        if(getWorld() == null) return;
        
        if(Greenfoot.mousePressed(null) || Greenfoot.mouseDragged(null))
        {
            Actor fruit = getOneIntersectingObject(Fruit.class);
            if(fruit != null && fruit.getWorld() != null)
            {
                ((Fruit)fruit).slice();
            }
            
            Actor bomb = getOneIntersectingObject(Bomb.class);
            if(bomb != null && bomb.getWorld() != null)
            {
                ((Bomb)bomb).explode();
            }
        }
    }
}