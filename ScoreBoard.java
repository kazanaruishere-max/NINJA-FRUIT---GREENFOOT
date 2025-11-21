import greenfoot.*;

public class ScoreBoard extends Actor
{
    public ScoreBoard()
    {
        updateScore(0, 1);
    }
    
    public void updateScore(int score, int level)
    {
        GreenfootImage image = new GreenfootImage(200, 50);
        image.setColor(new Color(0, 0, 0, 150));
        image.fillRect(0, 0, 200, 50);
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", true, false, 20));
        image.drawString("Score: " + score, 10, 25);
        image.drawString("Level: " + level, 10, 45);
        setImage(image);
    }
}