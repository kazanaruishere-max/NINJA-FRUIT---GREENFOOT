import greenfoot.*;

public class GameOver extends Actor
{
    private int finalScore;
    private int animationCounter = 0;
    private boolean buttonHighlight = false;
    
    public GameOver(int finalScore)
    {
        this.finalScore = finalScore;
        updateImage();
    }
    
    public void act()
    {
        // Animasi button blink
        animationCounter++;
        if(animationCounter % 30 == 0)
        {
            buttonHighlight = !buttonHighlight;
            updateImage();
        }
        
        // Cek klik mouse
        if(Greenfoot.mouseClicked(null))
        {
            restartGame();
        }
        
        // Atau tekan SPACE/ENTER
        if(Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter"))
        {
            restartGame();
        }
    }
    
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(450, 300);
        
        // Background
        image.setColor(new Color(0, 0, 0, 230));
        image.fillRect(0, 0, 450, 300);
        
        // Border
        image.setColor(new Color(255, 0, 0));
        image.drawRect(8, 8, 434, 284);
        image.drawRect(10, 10, 430, 280);
        
        // Title "GAME OVER" dengan shadow
        image.setColor(new Color(100, 0, 0));
        image.setFont(new Font("Arial", true, false, 55));
        image.drawString("GAME OVER", 52, 82);
        
        image.setColor(new Color(255, 50, 50));
        image.setFont(new Font("Arial", true, false, 55));
        image.drawString("GAME OVER", 50, 80);
        
        // Line separator
        image.setColor(Color.WHITE);
        image.fillRect(50, 100, 350, 2);
        
        // Final Score
        image.setColor(Color.YELLOW);
        image.setFont(new Font("Arial", true, false, 32));
        image.drawString("★ SCORE ★", 130, 150);
        
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", true, false, 40));
        image.drawString("" + finalScore, 200, 195);
        
        // PERBAIKAN: Button area tanpa RoundRect (gunakan Rect biasa)
        if(buttonHighlight)
        {
            // Background kuning terang
            image.setColor(new Color(255, 215, 0, 200));
            image.fillRect(100, 220, 250, 50);
            
            // Border kuning
            image.setColor(new Color(255, 255, 0));
            image.drawRect(100, 220, 250, 50);
            image.drawRect(101, 221, 248, 48);
        }
        else
        {
            // Background hijau
            image.setColor(new Color(50, 150, 50, 180));
            image.fillRect(100, 220, 250, 50);
            
            // Border hijau terang
            image.setColor(new Color(100, 255, 100));
            image.drawRect(100, 220, 250, 50);
            image.drawRect(101, 221, 248, 48);
        }
        
        // Button text
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", true, false, 24));
        image.drawString("CLICK TO RESTART", 115, 255);
        
        setImage(image);
    }
    
    private void restartGame()
    {
        Greenfoot.setWorld(new MyWorld());
    }
}